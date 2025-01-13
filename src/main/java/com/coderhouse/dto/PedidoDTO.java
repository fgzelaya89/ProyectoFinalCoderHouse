package com.coderhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private String descripcion;
    private Long clienteId; // Solo el ID del cliente
    private List<DetallePedidoDTO> detalles;
}

