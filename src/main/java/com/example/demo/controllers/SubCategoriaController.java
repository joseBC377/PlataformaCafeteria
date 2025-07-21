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

import com.example.demo.entities.SubCategoria;
import com.example.demo.services.SubCategoriaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/subcategoria", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor

public class SubCategoriaController {

    private final SubCategoriaService service;
    
     // Obtener todos las subcategorias
    @GetMapping("lista")
    public List<SubCategoria> selectAll() {
        return service.selectAll();
    }

    // Obtener una subcategoria por ID
    @GetMapping("lista/{id}")
    public SubCategoria selectSubCategoriaById(@PathVariable Integer id) {
        return service.selectId(id);
    }

    // Insertar una nueva subcategoria
    @PostMapping("insertar")
    public SubCategoria insertSubCategoria(@RequestBody SubCategoria subCategoria) {
        return service.insertSubCategoria(subCategoria);
    }

    // Actualizar una categoria existente
    @PutMapping("actualizar/{id}")
    public SubCategoria updateSubCategoria(@PathVariable Integer id, @RequestBody SubCategoria subCategoria) {
        subCategoria.setId(id); // No se puede cambiar el ID
        return service.updateSubCategoria(id, subCategoria);
    }

    // Eliminar una categoria
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deleteSubCategoria(@PathVariable Integer id) {
        service.deleteSubCategoria(id);
        return ResponseEntity.ok("SubCategoria eliminada correctamente. ID: " + id);
    }
}
