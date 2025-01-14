package com.coderhouse.repositories;

import com.coderhouse.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p JOIN FETCH p.detalles d")
    List<Pedido> findAllWithDetails();

    @Query("SELECT p FROM Pedido p JOIN FETCH p.detalles d WHERE p.id = :id")
    Optional<Pedido> findByIdWithDetails(@Param("id") Long id);
}
