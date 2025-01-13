package com.coderhouse.services;

import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto con ID " + id + " no encontrado."));
    }

    public Producto crearProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty() ||
                producto.getStock() == null || producto.getStock() < 0 ||
                producto.getPrecio() == null || producto.getPrecio() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos obligatorios faltantes o incorrectos.");
        }
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto productoExistente = obtenerProductoPorId(id);

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setCategoria(productoActualizado.getCategoria());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setPrecio(productoActualizado.getPrecio());

        return productoRepository.save(productoExistente);
    }

    public void eliminarProducto(Long id) {
        Producto productoExistente = obtenerProductoPorId(id);
        productoRepository.delete(productoExistente);
    }
}