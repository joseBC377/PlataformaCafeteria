package com.example.demo.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.Usuariorepository; 

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final Usuariorepository repository; // 
    private final PasswordEncoder passwordEncoder;

    // Obtener todos los usuarios
    public List<Usuario> selectAll() {
        return repository.findAll();
    }

    // Buscar usuario por ID
    public Usuario selectId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el usuario con id: " + id));
    }

    // Registrar nuevo usuario
    public Usuario insertUsuario(Usuario usuario) {
        String encriptado =passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(encriptado);
        return repository.save(usuario);
    }

    // Actualizar usuario
    public Usuario updateUsuario(Long id, Usuario usuario) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede actualizar, el usuario no existe");
        }
        usuario.setId(id); // Aseguramos que el ID es el correcto
        return repository.save(usuario);
    }

    // Eliminar usuario
    public void deleteUsuario(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, el usuario con id " + id + " no existe");
        }
        repository.deleteById(id);
    }
    // public Usuario findByCorreo(String correo){
    //     return repository.findByCorreo(correo).orElseThrow();
    // }
}
