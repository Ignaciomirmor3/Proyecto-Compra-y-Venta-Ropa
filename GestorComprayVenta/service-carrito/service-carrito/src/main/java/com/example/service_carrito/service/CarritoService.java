package com.example.service_carrito.service;

import com.example.service_carrito.model.Carrito;
import com.example.service_carrito.repository.CarritoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> listarTodos() {
        return carritoRepository.findAll();
    }

    public Optional<Carrito> buscarPorId(Long id) {
        return carritoRepository.findById(id);
    }

    public Carrito guardar(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void eliminar(Long id) {
        carritoRepository.deleteById(id);
    }
    
    //Logica Financiera
    public Integer calcularTotalAPagar() {
        List<Carrito> prendasEnCarrito = carritoRepository.findAll();
        
        // aquí se implementará la comunicación con service-catalogo (WebClient/Feign)
        // para buscar el precio de cada 'idProducto' y sumarlos.
        // Por ahora, retornamos 0 para dejar la estructura lista.
        
        return 0; 
    }
}