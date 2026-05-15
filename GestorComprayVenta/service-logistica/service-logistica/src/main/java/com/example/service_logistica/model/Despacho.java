package com.example.service_logistica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
    @Column(name="Nro. Orden", nullable = false)
    private Long nro_Orden;

    @Column(name="Dirección de Envío", nullable = false, length = 200)
    private String direccion_Envio;

    @Column(name="Comuna", nullable = false, length = 200)
    private String comuna;

    // Llaves foráneas
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Estado_Despacho")
    private EstadoDespacho estadoDespacho;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Metodo_Entrega")
    private MetodoEntrega metodoEntrega;
}
