package com.example.service_logistica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service_logistica.model.Despacho;
import com.example.service_logistica.repository.DespachoRepository;

@Service
public class DespachoService {

    @Autowired
    private DespachoRepository despachoRepository;

    public List<Despacho> listarDespachos(){
        return despachoRepository.findAll();
    }

    public Despacho crearDespacho(Despacho despacho){
        return despachoRepository.save(despacho);
    }

    public Optional<Despacho> encontrarDespacho(Long id){
        return despachoRepository.findById(id);
    }

    public Despacho actualizarDespacho(Long id, Despacho despacho) {
        Despacho existente = despachoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setEstadoDespacho(despacho.getEstadoDespacho());
            return despachoRepository.save(existente);
        }
        return null;
    }

    public void eliminarDespacho(Long id) {
        despachoRepository.deleteById(id);
    }





    public List<Despacho> filtrarDespachoYEntrega(Long idDespacho, Long idEntrega){
        return despachoRepository.findByDespachoYEntrega(idDespacho, idEntrega);
    }
}
