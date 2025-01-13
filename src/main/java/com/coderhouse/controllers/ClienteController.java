package com.coderhouse.controllers;

import com.coderhouse.models.Cliente;
import com.coderhouse.services.ClienteService;
import com.coderhouse.exceptions.ClienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes();
    }

    @GetMapping("/buscar/{dni}")
    public ResponseEntity<?> obtenerClientePorDni(@PathVariable int dni) {
        try {
            Cliente cliente = clienteService.obtenerClientePorDni(dni);
            return ResponseEntity.ok(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Cliente> crearNuevoCliente(@RequestBody Cliente cliente) {

        if (cliente.getCodigoCliente() == null || cliente.getCodigoCliente().isEmpty()) {
            cliente.setCodigoCliente("CL-" + cliente.getDni());
        }
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }
}
