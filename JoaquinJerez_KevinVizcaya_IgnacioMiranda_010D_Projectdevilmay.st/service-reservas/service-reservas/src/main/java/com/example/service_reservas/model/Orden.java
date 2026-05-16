package com.example.service_reservas.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Nro. de Orden", nullable = false)
    private Long nro_Orden;

    // Referencia al otro microservicio
    @jakarta.validation.constraints.NotNull(message = "El ID del carrito no puede ser nulo")
    @Column(name="ID Carrito", nullable = false)
    private Long id_Carrito;

    @jakarta.validation.constraints.PositiveOrZero(message = "El precio total debe ser cero o positivo")
    @Column(name="Precio Total", nullable = false)
    private double precio_Total;

    @Column(name = "fecha_Hora", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fecha_Hora = LocalDateTime.now();

    @jakarta.validation.constraints.NotBlank(message = "El nombre del cliente no puede estar vacío")
    @Column(name="Nombre Cliente", nullable = false, length = 200)
    private String nombre_Cliente;

    @jakarta.validation.constraints.NotBlank(message = "El RUT del cliente no puede estar vacío")
    @Column(name="RUT Cliente", nullable = false, length = 10)
    private String rut_Cliente;

    @Column(name="Instagram de Cliente", nullable = false, length = 30)
    private String instagram_Cliente;

    @jakarta.validation.constraints.NotNull(message = "El teléfono de respaldo no puede ser nulo")
    @Column(name="Telefono de Respaldo", nullable = false)
    private Integer telefono_Respaldo;

    @jakarta.validation.constraints.NotBlank(message = "El correo de respaldo no puede estar vacío")
    @jakarta.validation.constraints.Email(message = "El formato del correo es inválido")
    @Column(name="Correo de Respaldo", nullable = false, length = 100)
    private String correo_Respaldo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Metodo_Pago")
    private MetodoPago metodoPago;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Estado_Orden")
    private EstadoOrden estadoOrden;

    // No se guardará en la base de datos, solo se usará para mostrar los datos de logística al obtener la orden
    @Transient
    private Object datosDespacho; 
}
