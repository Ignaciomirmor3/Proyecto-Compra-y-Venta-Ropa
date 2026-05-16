package com.example.service_logistica.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service_logistica.model.Despacho;
import com.example.service_logistica.repository.DespachoRepository;

@Slf4j
@Service
public class DespachoService {

    @Autowired
    private DespachoRepository despachoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Despacho> listarDespachos(){
        return despachoRepository.findAll();
    }

    public Despacho crearDespacho(Despacho despacho){
        log.info("Creando despacho para orden nro: {}", despacho.getNro_Orden());
        Despacho guardado = despachoRepository.save(despacho);
        notificarEstadoAReservas(guardado);
        return guardado;
    }

    public Optional<Despacho> encontrarDespacho(Long id){
        return despachoRepository.findById(id);
    }

    public Despacho actualizarDespacho(Long id, Despacho despacho) {
        log.info("Actualizando despacho con id: {}", id);
        Despacho existente = despachoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setEstadoDespacho(despacho.getEstadoDespacho());
            Despacho actualizado = despachoRepository.save(existente);
            notificarEstadoAReservas(actualizado);
            return actualizado;
        }
        return null;
    }

    private void notificarEstadoAReservas(Despacho despacho) {
        if (despacho.getNro_Orden() != null && despacho.getEstadoDespacho() != null) {
            try {
                String nombreEstado = despacho.getEstadoDespacho().getNombre_Estado();
                String reservasUrl = "http://localhost:8084/api/v1/reservas/ordenes/" + despacho.getNro_Orden() + "/estado?estado=" + nombreEstado;
                log.info("Notificando estado {} a reservas: {}", nombreEstado, reservasUrl);
                // Usamos WebClient PUT
                webClientBuilder.build()
                        .put()
                        .uri(reservasUrl)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .subscribe();
            } catch (Exception e) {
                log.error("Error al comunicar con service-reservas: {}", e.getMessage());
            }
        }
    }

    public void eliminarDespacho(Long id) {
        despachoRepository.deleteById(id);
    }





    public List<Despacho> filtrarDespachoYEntrega(Long idDespacho, Long idEntrega){
        return despachoRepository.findByDespachoYEntrega(idDespacho, idEntrega);
    }
}
