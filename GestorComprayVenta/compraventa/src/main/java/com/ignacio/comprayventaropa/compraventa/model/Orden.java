package com.ignacio.comprayventaropa.compraventa.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orden")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_orden;

    private Date fechaHora;

    private String nombreCliente;

    private String rutCliente;

    private String usuarioInsta;

    private Integer telefonoRespaldo;

    private String correoCliente;

    private String metodoEntrega;

    private String direccionEnvio;

    private String comuna;

    private String metodoPago;

    private String estadoOrden;

}
