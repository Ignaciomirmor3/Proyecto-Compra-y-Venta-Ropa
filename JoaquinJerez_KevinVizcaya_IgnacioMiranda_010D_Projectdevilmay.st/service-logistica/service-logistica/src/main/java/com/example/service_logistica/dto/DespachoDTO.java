package com.example.service_logistica.dto;

import com.example.service_logistica.model.EstadoDespacho;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DespachoDTO {
    private Long id;

    @NotNull(message = "El número de orden no puede ser nulo")
    private Long nro_Orden;

    @NotBlank(message = "La dirección de envío no puede estar vacía")
    private String direccion_Envio;

    @NotBlank(message = "La comuna no puede estar vacía")
    private String comuna;

    @NotNull(message = "El estado de despacho es obligatorio")
    private EstadoDespacho estadoDespacho;

    // Lo renombramos a idEntrega para que calce exactamente con tu Postman
    private Long idEntrega; 
}