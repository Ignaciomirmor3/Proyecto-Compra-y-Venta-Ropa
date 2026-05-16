package com.example.service_reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.service_reservas.model.EstadoOrden;

@Repository
public interface EstadoOrdenRepository extends JpaRepository<EstadoOrden, Long> {
	@org.springframework.data.jpa.repository.Query("SELECT e FROM EstadoOrden e WHERE e.nombre_Estado = ?1")
	java.util.Optional<EstadoOrden> findByNombre_Estado(String nombre_Estado);
}
