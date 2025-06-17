package com.example.demo.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Rol;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.Usuariorepository;
import com.example.demo.util.AuthenticationRequest;
import com.example.demo.util.AuthenticationResponse;
import com.example.demo.util.RefreshTokenRequest;
import com.example.demo.util.RegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final Usuariorepository usuariorepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var user = Usuario.builder()
        .nombre(request.firstname())
        .apellido(request.lastname())
        .correo(request.email())
        .telefono(request.phone())
        .contrasena(passwordEncoder.encode(request.password()))
        .rol(Rol.CLIENTE)
        .build();
        usuariorepository.save(user);
        var jwToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return new AuthenticationResponse(jwToken, refreshToken);
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = usuariorepository.findByCorreo(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse refreshToken (RefreshTokenRequest request){
        String userEmail = jwtService.extractUsername(request.refreshToken());
        if (userEmail != null) {
            var user = usuariorepository.findByCorreo(userEmail).orElseThrow();
            if (jwtService.isTokenValid(request.refreshToken(), user)) {
                var accessToken = jwtService.generateToken(user);
                return new AuthenticationResponse(accessToken, request.refreshToken());
            }
        }
        throw new RuntimeException("Token de refresco Invalido");
    }

}
