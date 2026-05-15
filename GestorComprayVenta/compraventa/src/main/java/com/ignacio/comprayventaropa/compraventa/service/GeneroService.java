package com.ignacio.comprayventaropa.compraventa.service;

import com.ignacio.comprayventaropa.compraventa.model.Genero;
import com.ignacio.comprayventaropa.compraventa.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

}