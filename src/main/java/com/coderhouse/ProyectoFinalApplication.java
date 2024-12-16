package com.coderhouse;

import com.coderhouse.models.Cliente;
import com.coderhouse.models.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.dao.DaoFactory;


@SpringBootApplication
public class ProyectoFinalApplication implements CommandLineRunner {

	@Autowired
	private DaoFactory dao;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {

			Pedido pedido1 = new Pedido("Mate");
			Pedido pedido2 = new Pedido("cafe");
			Pedido pedido3 = new Pedido("harina");
			Pedido pedido4 = new Pedido("Palmitos");

			Cliente cliente1 = new Cliente("Alejandro", " Di Stefano", 12333444, "L255333444");
			Cliente cliente2 = new Cliente("Martin", "Ferreyra", 13555222, "L3355555222");
			Cliente cliente3 = new Cliente("Rodrigo", "Berger", 11333444, "L2135533444");
			Cliente cliente4 = new Cliente("Maria Rocio", "Ahumada", 15333444, "L5533553444");

			dao.eliminarTodosPedidos();
			dao.eliminarTodosClientes();


			dao.persistirCliente(cliente1);
			dao.persistirCliente(cliente2);
			dao.persistirCliente(cliente3);
			dao.persistirCliente(cliente4);

			dao.persistirPedido(pedido1);
			dao.persistirPedido(pedido2);
			dao.persistirPedido(pedido3);
			dao.persistirPedido(pedido4);

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

	}

}
