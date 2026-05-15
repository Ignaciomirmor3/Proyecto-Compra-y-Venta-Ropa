package com.ignacio.comprayventaropa.compraventa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ignacio.comprayventaropa.compraventa.model.TipoPrenda;
import java.util.List;


@Repository
public interface TipoPrendaRepository extends JpaRepository<TipoPrenda, Long>{

    //Encontrar por nombre de tipo prenda
    List<TipoPrenda> findByNombreTipo(String nombreTipo);

}
