package com.coderhouse.dto;

import com.coderhouse.models.DetallePedido;
import com.coderhouse.models.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public PedidoDTO toDTO(Pedido pedido) {
        List<DetallePedidoDTO> detallesDTO = pedido.getDetalles().stream()
                .map(this::detalleToDTO)
                .collect(Collectors.toList());

        return new PedidoDTO(
                pedido.getId(),
                pedido.getDescripcion(),
                pedido.getCliente().getId(),
                detallesDTO
        );
    }

    public DetallePedidoDTO detalleToDTO(DetallePedido detalle) {
        return new DetallePedidoDTO(
                detalle.getId(),
                detalle.getProducto().getId(),
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getMontoParcial()
        );
    }
}