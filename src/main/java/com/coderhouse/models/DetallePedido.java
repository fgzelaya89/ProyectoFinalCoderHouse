package com.coderhouse.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "DetallePedidos")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    private int cantidad;
    private double monto; // Precio unitario
    private double montoParcial; // Calculado automáticamente

    @PrePersist
    @PreUpdate
    private void calcularMontoParcial() {
        this.montoParcial = this.cantidad * this.monto;
    }
}