package com.ignacio.comprayventaropa.compraventa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ignacio.comprayventaropa.compraventa.model.Producto;
import java.util.List;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    //Encontrar producto por ID
    List<Producto> findById_producto(Integer id_producto);

    //Encontrar productos por talla
    List<Producto> findByTallaProducto(String tallaProducto);

    //Encontrar productos por estado de inventario
    List<Producto> findByEstadoInventario(String estadoInventario);

}
