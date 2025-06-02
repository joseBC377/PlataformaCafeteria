package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Productos;
import com.example.demo.repositories.ProductoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductosService {
    private final ProductoRepository repository;

    public List<Productos> selectAll() {
        return repository.findAll();
    }

    public Productos selectId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el id: " + id));
    }

    public Productos insert(Productos productos) {
        return repository.save(productos);
    }

    public Productos updateProductos(Integer id, Productos productos) {
        if (!repository.existsById(productos.getId())) {
            throw new RuntimeException("No se encontró el id " + productos.getId());
        }
        return repository.save(productos);
    }

    public void deleteProductos(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se encuentra el id: " + id);
        }
        repository.deleteById(id);
        System.out.println("Mensaje eliminado " + id);
    }
}
