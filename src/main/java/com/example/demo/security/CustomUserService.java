package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final UsuarioService service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario;
       try {
        usuario= service.findByCorreo(username);
        return new CustomUser(usuario);
       } catch (Exception e) {
        throw new UsernameNotFoundException("Usuario no encontrado");
    }
    }

}
