package com.coderhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoDTO {
    private Long id;
    private Long productoId; // Solo el ID del producto
    private String productoNombre; // Nombre del producto
    private int cantidad;
    private double montoParcial;
}

