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

@Entity
@Table(name = "Clientes")
public class Cliente {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    private String apellido;

    @Column(unique = true, nullable = false) // Va a ser Unico y No Nulo
    private int dni;

    @Column(unique = true, nullable = false)
    private String codigoCliente;

    @ManyToMany(mappedBy = "clientes", fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>();

    private LocalDateTime createdAt;

    public Cliente() {
        super();
    }

    public Cliente(String nombre, String apellido, int dni, String codigoCliente) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.codigoCliente = codigoCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", codigoCliente="
                + codigoCliente + ", pedidos=" + pedidos + ", createdAt=" + createdAt + "]";
    }
}
