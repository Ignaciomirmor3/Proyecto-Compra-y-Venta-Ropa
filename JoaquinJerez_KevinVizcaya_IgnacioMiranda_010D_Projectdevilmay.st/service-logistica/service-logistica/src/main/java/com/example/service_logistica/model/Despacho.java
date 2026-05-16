package com.example.service_logistica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Despacho {

    @Id
    @jakarta.validation.constraints.NotNull(message = "El Nro de orden no puede ser nulo")
    @Column(name="Nro. Orden", nullable = false)
    private Long nro_Orden;

    @jakarta.validation.constraints.NotBlank(message = "La dirección de envío no puede estar vacía")
    @Column(name="Dirección de Envío", nullable = false, length = 200)
    private String direccion_Envio;

    @jakarta.validation.constraints.NotBlank(message = "La comuna no puede estar vacía")
    @Column(name="Comuna", nullable = false, length = 200)
    private String comuna;

    // Llaves foráneas

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Estado_Despacho")
    private EstadoDespacho estadoDespacho;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Metodo_Entrega")
    private MetodoEntrega metodoEntrega;
}
