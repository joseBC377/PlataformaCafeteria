package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entities.Rol;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.Usuariorepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	//usuario administrador
	@Bean
	CommandLineRunner commandLineRunner(Usuariorepository repository, PasswordEncoder passwordEncoder){
		return args -> {
			if (repository.findByCorreo("jose@gmail.com").isEmpty()) {
				Usuario usuario = new Usuario();
				usuario.setNombre("Jose");
				usuario.setApellido("Balcazar");
				usuario.setCorreo("jose@gmail.com");
				usuario.setContrasena(passwordEncoder.encode("12345678"));
				usuario.setRol(Rol.ADMIN);
				usuario.setTelefono("978158575");
				repository.save(usuario);

			}
		};
	}

}
