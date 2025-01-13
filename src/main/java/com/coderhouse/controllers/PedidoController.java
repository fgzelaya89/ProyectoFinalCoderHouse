package com.coderhouse.controllers;

import com.coderhouse.models.Pedido;
import com.coderhouse.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        System.out.println("nuevoPedido: "+nuevoPedido.toString());
        return ResponseEntity.status(201).body(nuevoPedido);
    }
}
