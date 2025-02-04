package com.cargafacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.cargafacil.config.JwtUtil;
import com.cargafacil.dto.AuthRequest;
import com.cargafacil.dto.AuthResponse;
import com.cargafacil.service.MyUserDetailsService;

import org.springframework.http.ResponseEntity;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getCorreo(), authRequest.getPassword())
            );

            String rol = userDetailsService.obtenerRolPorCorreo(authRequest.getCorreo());

            // Generar el token JWT con el correo y el rol
            final String jwt = jwtUtil.generateToken(authRequest.getCorreo(), rol);

            // Crear la respuesta con el token y el código de éxito
            return ResponseEntity.ok(new AuthResponse(jwt, "success", 200));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}

