package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Activa el perfil 'test' para esta clase de prueba

class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
