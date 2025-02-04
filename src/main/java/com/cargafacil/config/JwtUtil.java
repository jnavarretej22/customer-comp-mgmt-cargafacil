package com.cargafacil.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cargafacil.dto.parametro;
import com.cargafacil.service.ParametroService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
	@Autowired
	private ParametroService parametroService;
	
    // Generar la clave secreta utilizando Keys.hmacShaKeyFor
    private SecretKey SECRET_KEY = Keys.hmacShaKeyFor("kJ8$wq9tP4aBz7LxR2mN6fUvXy!Hs3Dp".getBytes());

    // Extraer el username del token JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extraer la fecha de expiración del token JWT
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extraer cualquier reclamación del token JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Usar la clave secreta generada
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validar el token JWT comparando el username extraído con el esperado y verificando si ha expirado
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Generar un token JWT con el username y un tiempo de expiración de 10 horas
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // Agregar el rol como un claim
        return createToken(claims, username);
    }


    // Crear el token JWT firmándolo con la clave secreta
    private String createToken(Map<String, Object> claims, String subject) {
        parametro parame = parametroService.obtenerPorNombreParametro("CONFIG_LOGIN");

        return Jwts.builder()
                .setClaims(claims)  // Incluye los claims (como el rol)
                .setSubject(subject)  // El usuario o correo electrónico
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * parame.getValor1()))  // Expiración (ej. 10 horas)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)  // Firma el token
                .compact();
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

}
