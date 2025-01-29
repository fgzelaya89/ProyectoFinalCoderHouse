package com.coderhouse.services;

import com.coderhouse.models.Cliente;
import com.coderhouse.models.DetallePedido;
import com.coderhouse.models.Pedido;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.PedidoRepository;
import com.coderhouse.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class PedidoService {
    private static final String WORLD_CLOCK_API = "http://worldclockapi.com/api/json/utc/now";


    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Pedido> obtenerTodosPedidosConDetalles() {
        return pedidoRepository.findAllWithDetails();
    }

    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }


    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Pedido addPedido(Pedido pedido) {

        // Validar los clientes
        List<Cliente> clientes = pedido.getClientes().stream()
                .map(cliente -> clienteRepository.findById(cliente.getId())
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + cliente.getId())))
                .toList();

        // Validar los detalles del pedido y calcular el monto total
        double montoTotal = 0;
        for (DetallePedido detalle : pedido.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalle.getProducto().getId()));

            detalle.setProducto(producto);
            detalle.setMonto(producto.getPrecio());
            detalle.setMontoParcial(detalle.getCantidad() * producto.getPrecio());
            montoTotal += detalle.getMontoParcial();
        }

        // Asignar los clientes y detalles al pedido
        pedido.setClientes(clientes);
        pedido.setMontoTotal(montoTotal);

        // Obtener la fecha del servicio o usar LocalDateTime.now() como fallback
        pedido.setFechaHora(obtenerFechaActual());

        // Guardar el pedido
        return pedidoRepository.save(pedido);
    }

    private LocalDateTime obtenerFechaActual() {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(WORLD_CLOCK_API, String.class);
            // Parsear el resultado
            if (response != null && response.contains("currentDateTime")) {
                String fecha = response.split("\"currentDateTime\":\"")[1].split("\"")[0];
                return LocalDateTime.parse(fecha, DateTimeFormatter.ISO_DATE_TIME);
            }
        } catch (RestClientException e) {
            System.out.println("Fallo al obtener la fecha del API, se usará la fecha local.");
        }
        return LocalDateTime.now();
    }
}
