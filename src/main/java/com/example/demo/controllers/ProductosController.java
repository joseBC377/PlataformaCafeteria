package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Productos;
import com.example.demo.services.ProductosService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/productos", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProductosController {
    
    private final ProductosService service;
    
    // Obtener todos los productos
    @GetMapping("lista")
    public List<Productos> selectAllUsers() {
        return service.selectAll();
    }

    // Obtener producto por ID
    @GetMapping("lista/{id}")
    public Productos selectProductosById(@PathVariable Integer id) {
        return service.selectId(id);
    }

    // Insertar nuevos productos
    @PostMapping("insertar")
    public Productos insertProductos(@RequestBody Productos productos) {
        return service.insert(productos);
    }

    // Actualizar un producto existente
    @PutMapping("actualizar/{id}")
    public Productos updateProductos(@PathVariable Integer id, @RequestBody Productos productos) {
        productos.setId(id); // No se puede cambiar el ID
        return service.updateProductos(id, productos);
    }

    // Eliminar un producto
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deleteProductos(@PathVariable Integer id) {
        service.deleteProductos(id);
        return ResponseEntity.ok("Productos eliminada correctamente. ID: " + id);
    }
    
}
