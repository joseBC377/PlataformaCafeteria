package com.example.demo.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;
    private Integer refreshTokenExpirationAfterDays;

    // Obtencion de expiracion en milisegundos
    public long getTokenExpirationInMillis() {
        return tokenExpirationAfterDays * 24L * 60 * 60 * 1000;
    }

    // refresco de expiracion del token
    public long getRefreshTokenExpirationInMillis() {
        return refreshTokenExpirationAfterDays * 24L * 60 * 60 * 1000;
    }

    @Bean(name = "jwtSecretKey")
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

}
