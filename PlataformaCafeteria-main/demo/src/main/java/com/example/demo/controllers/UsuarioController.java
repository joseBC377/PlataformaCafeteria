package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    // Obtener todos los usuarios
    @GetMapping("lista")
    public List<Usuario> selectAllUsers() {
        return service.selectAll();
    }

    // Obtener un usuario por ID
    @GetMapping("lista/{id}")
    public Usuario selectUserById(@PathVariable Long id) {
        return service.selectId(id);
    }

    // Insertar un nuevo usuario
    @PostMapping("insertar")
    public Usuario insertUser(@RequestBody Usuario usuario) {
        return service.insertUsuario(usuario);
    }

    // Actualizar un usuario existente
    @PutMapping("actualizar/{id}")
    public Usuario updateUser(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id); // No se puede cambiar el ID
        return service.updateUsuario(id, usuario);
    }

    // Eliminar un usuario
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        service.deleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado correctamente. ID: " + id);
    }
}
