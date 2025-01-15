package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Clientes")
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String email;

    @Column(unique = true, nullable = false)
    private int dni;

    @Column(unique = true, nullable = false)
    private String codigoCliente;

    @ManyToMany(mappedBy = "clientes", fetch = FetchType.LAZY)
    @JsonBackReference // Evita recursividad
    private List<Pedido> pedidos = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}

