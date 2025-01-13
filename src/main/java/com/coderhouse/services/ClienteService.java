package com.coderhouse.services;

import com.coderhouse.exceptions.ClienteNotFoundException;
import com.coderhouse.models.Cliente;
import com.coderhouse.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorDni(int dni) {
        if (dni <= 0) {
            throw new IllegalArgumentException("El DNI debe ser un número positivo.");
        }

        return clienteRepository.findByDni(dni)
                .orElseThrow(() -> new ClienteNotFoundException("No se encontró un cliente con el DNI: " + dni));
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
