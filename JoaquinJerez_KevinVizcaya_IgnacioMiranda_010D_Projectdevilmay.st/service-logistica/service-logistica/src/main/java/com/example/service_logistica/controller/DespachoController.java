package com.example.service_logistica.controller;

import com.example.service_logistica.dto.DespachoDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service_logistica.model.Despacho;
import com.example.service_logistica.model.EstadoDespacho;
import com.example.service_logistica.model.MetodoEntrega;
import com.example.service_logistica.service.DespachoService;

@Slf4j
@RestController
@RequestMapping("/api/v2/logistica/despacho")
public class DespachoController {

    @Autowired
    private DespachoService despachoService;


    
    @GetMapping
    public ResponseEntity<List<Despacho>> Listar(){
        List<Despacho> despachos = despachoService.listarDespachos();
        if (despachos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(despachos);
    }

@PostMapping
public ResponseEntity<Despacho> Crear(@jakarta.validation.Valid @RequestBody DespachoDTO despachoDTO){
    log.info("Llamada a POST /api/v2/logistica/despacho con orden nro: {}", despachoDTO.getNro_Orden());
    
    Despacho despacho = new Despacho();
    despacho.setNro_Orden(despachoDTO.getNro_Orden());
    
    // Ahora que el DTO los tiene, los asignamos correctamente a la entidad
    despacho.setDireccion_Envio(despachoDTO.getDireccion_Envio());
    despacho.setComuna(despachoDTO.getComuna());
    
    // Forzamos el estado en el código para asegurar que la inserción no sea null
    EstadoDespacho estado = new EstadoDespacho();
    estado.setNombre_Estado("EN_PREPARACION");
    despacho.setEstadoDespacho(estado);
    
    // Usamos el nuevo idEntrega del DTO que calza con Postman
    if (despachoDTO.getIdEntrega() != null) {
        MetodoEntrega me = new MetodoEntrega();
        me.setId_Metodo_Entrega(despachoDTO.getIdEntrega());
        despacho.setMetodoEntrega(me);
    }
    
    return ResponseEntity.ok(despachoService.crearDespacho(despacho));
}

    @GetMapping("/{id}")
    public ResponseEntity<Despacho> EncontrarPorId(@PathVariable Long id){
        return despachoService.encontrarDespacho(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despacho> Actualizar(@PathVariable Long id, @jakarta.validation.Valid @RequestBody DespachoDTO despachoDTO){
        log.info("Llamada a PUT /api/v2/logistica/despacho/{} para actualizar estado", id);
        
        Despacho despacho = new Despacho();
        despacho.setNro_Orden(despachoDTO.getNro_Orden());
        despacho.setDireccion_Envio(despachoDTO.getDireccion_Envio());
        despacho.setComuna(despachoDTO.getComuna());
        
        // Forzamos el estado para evitar que Hibernate reciba un null transitorio
        EstadoDespacho estado = new EstadoDespacho();
        estado.setNombre_Estado("EN_PREPARACION");
        despacho.setEstadoDespacho(estado);
        
        // 🔥 CORRECCIÓN: Cambiamos getMetodoEntrega() por getIdEntrega()
        if (despachoDTO.getIdEntrega() != null) {
            MetodoEntrega me = new MetodoEntrega();
            me.setId_Metodo_Entrega(despachoDTO.getIdEntrega());
            despacho.setMetodoEntrega(me);
        }
        
        Despacho actualizado = despachoService.actualizarDespacho(id, despacho);
        if (actualizado != null){
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable Long id) {
        try {
            despachoService.eliminarDespacho(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



    

    @GetMapping("/filtrar")
    public ResponseEntity<?> Filtrar(
        @RequestParam(required = false) Long idDespacho, 
        @RequestParam(required = false) Long idEntrega){

            List<Despacho> lista = despachoService.filtrarDespachoYEntrega(idDespacho, idEntrega);

            if (lista.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(lista);
        }
}
