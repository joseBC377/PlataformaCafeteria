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

import com.example.demo.entities.Categoria;
import com.example.demo.services.CategoriaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CategoriaController {
    private final CategoriaService service;
    // Obtener todos las categorias
    @GetMapping("lista")
    public List<Categoria> selectAll() {
        return service.selectAll();
    }

    // Obtener una categoria por ID
    @GetMapping("lista/{id}")
    public Categoria selectCategoriaById(@PathVariable Integer id) {
        return service.selectId(id);
    }

    // Insertar una nueva categoria
    @PostMapping("insertar")
    public Categoria insertCategoria(@RequestBody Categoria categoria) {
        return service.insert(categoria);
    }

    // Actualizar una categoria existente
    @PutMapping("actualizar/{id}")
    public Categoria updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id); // No se puede cambiar el ID
        return service.updateCategoria(id, categoria);
    }

    // Eliminar una categoria
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deleteCategoria(@PathVariable Integer id) {
        service.deleteCategoria(id);
        return ResponseEntity.ok("Categoria eliminada correctamente. ID: " + id);
    }
}
