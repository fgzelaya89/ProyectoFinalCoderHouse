package com.coderhouse.services;

import com.coderhouse.models.Cliente;
import com.coderhouse.models.DetallePedido;
import com.coderhouse.models.Pedido;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.PedidoRepository;
import com.coderhouse.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Pedido crearPedido(Pedido pedido) {
        Double montoTotal = 0.0;

        for (DetallePedido detalle : pedido.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Producto con ID " + detalle.getProducto().getId() + " no encontrado."));

            // Verificar stock disponible
            if (producto.getStock() < detalle.getCantidad()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Actualizar stock del producto
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            // Calcular el monto parcial y acumular el total
            Double montoParcial = detalle.getCantidad() * producto.getPrecio();
            detalle.setMontoParcial(montoParcial);
            detalle.setPedido(pedido);
            montoTotal += montoParcial;
        }

        // Asignar el monto total al pedido
        pedido.setMontoTotal(montoTotal);

        // Guardar el pedido
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerTodosPedidosConDetalles() {
        return pedidoRepository.findAllWithDetails();
    }

    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    //////////////

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

        // Guardar el pedido
        return pedidoRepository.save(pedido);
    }
}
