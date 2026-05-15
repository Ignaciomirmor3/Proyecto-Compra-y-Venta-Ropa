package com.example.service_catalogo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID Producto", nullable = false)
    private Long id_producto;

    @Column(name="Nombre", nullable = false, length = 200)
    private String nombreProducto;
    
    @Column(name="Descripción", nullable = false, length = 500)
    private String descripcionProducto;

    @Column(name="Precio", nullable = false )
    private Integer precioProducto;

    @Column(name="Talla", nullable = false, length = 5)
    private String tallaProducto;

    @Column(name="Estado",nullable = false, length = 50)
    private String estadoInventario;

    @Column(name="URL Imagen", nullable = false, length = 800)
    private String urlImagen;

    // claves foraneas genero y tipoprenda (nose como implementarlas)

    @ManyToOne
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    private TipoPrenda tipoPrenda;

}
