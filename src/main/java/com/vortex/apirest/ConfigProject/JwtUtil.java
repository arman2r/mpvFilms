package com.vortex.apirest.ConfigProject;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "T0+T1TMouyL4QKI9x+HO05+gpOuiwKZaR4lhbBxfv50="; // Cambia esto
    private static final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos

    private final SecretKey key;

    public JwtUtil() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(decodedKey);
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key) // Nuevo método de firma
                .compact();
    }

    public String extractEmail(String token) {
        Claims claims = Jwts.parser() // Nuevo método en JJWT 0.12.x
                .verifyWith(key) // Verificación con clave
                .build()
                .parseSignedClaims(token) // Nuevo método
                .getPayload(); // Obtiene los claims

        return claims.getSubject(); // Obtiene el email
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(key) // Verifica la firma con una SecretKey
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}