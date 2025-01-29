package com.coderhouse.controllers;


import com.coderhouse.exceptions.ClienteNotFoundException;
import com.coderhouse.exceptions.ProductoNoEncontradoException;
import com.coderhouse.exceptions.ProductoSinStockException;
import com.coderhouse.models.Pedido;
import com.coderhouse.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> obtenerPedidosConDetalles() {
        return pedidoService.obtenerTodosPedidosConDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPedidoPorId(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.obtenerPedidoPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/addPedido")
    public ResponseEntity<?> addPedido(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.addPedido(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
        } catch (ClienteNotFoundException | ProductoNoEncontradoException | ProductoSinStockException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
