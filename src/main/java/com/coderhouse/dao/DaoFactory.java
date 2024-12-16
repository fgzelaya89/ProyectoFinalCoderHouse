package com.coderhouse.dao;

import com.coderhouse.models.Cliente;
import com.coderhouse.models.Pedido;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DaoFactory {

	@PersistenceContext
	private EntityManager em;


	@Transactional
	public void persistirCliente(Cliente cliente) {
		em.persist(cliente);
	}

	@Transactional
	public void persistirPedido(Pedido pedido) {
		em.persist(pedido);
	}

	@Transactional
	public void eliminarPedido(Pedido pedido) {
		Pedido pedidoToRemove = em.merge(pedido); // Asegúrate de que la entidad esté en el contexto de persistencia
		em.remove(pedidoToRemove);
	}

	@Transactional
	public void eliminarCliente(Cliente cliente) {
		Cliente clienteToRemove = em.merge(cliente); // Asegúrate de que la entidad esté en el contexto de persistencia
		em.remove(clienteToRemove);
	}

	@Transactional
	public void eliminarTodosPedidos() {
		em.createQuery("DELETE FROM Pedido p").executeUpdate();
	}

	@Transactional
	public void eliminarTodosClientes() {
		em.createQuery("DELETE FROM Cliente c").executeUpdate();
	}
}
