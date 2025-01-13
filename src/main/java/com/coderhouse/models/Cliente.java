package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Clientes")
@Data
@NoArgsConstructor
public class Cliente {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    private String apellido;
    private String email;

    @Column(unique = true, nullable = false) // Va a ser Unico y No Nulo
    private int dni;

    @Column(unique = true, nullable = false)
    private String codigoCliente;

    @ManyToMany(mappedBy = "clientes", fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>();

    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", dni=" + dni +
                ", codigoCliente='" + codigoCliente + '\'' +
                ", pedidos=" + pedidos +
                ", createdAt=" + createdAt +
                '}';
    }
}
