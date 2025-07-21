package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile; // ¡Importa esta anotación!

import com.example.demo.repositories.Usuariorepository;
// Asumo que aquí inyectas tu UsuarioRepository

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Este es el método CommandLineRunner que causa el problema en las pruebas
    @Bean
    @Profile("!test") // ¡IMPORTANTE! Esta anotación hace que este bean NO se active en el perfil 'test'
    public CommandLineRunner commandLineRunner(Usuariorepository usuarioRepository) {
        return args -> {
            // Tu lógica actual que intenta acceder a la tabla USUARIO, por ejemplo:
            // if (usuarioRepository.findByCorreo("algun@correo.com").isEmpty()) {
            //    // Crear y guardar un usuario si no existe
            // }
            System.out.println("Ejecutando CommandLineRunner para inicializar datos (solo en desarrollo/producción)...");
            // ... aquí va tu código que interactúa con la base de datos
        };
    }
}