package com.ignacio.comprayventaropa.compraventa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ignacio.comprayventaropa.compraventa.model.Genero;
import java.util.List;


@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{

    //Encontrar por nombre de genero
    List<Genero>findByNombreGenero(String nombreGenero);

}
