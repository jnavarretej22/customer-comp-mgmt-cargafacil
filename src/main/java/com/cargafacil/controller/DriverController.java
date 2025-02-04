package com.cargafacil.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cargafacil.dto.UserType;
import com.cargafacil.dto.User;
import com.cargafacil.repository.UserTypeRepository;
import com.cargafacil.service.UserService;

@RestController
@RequestMapping("/driver") // Definimos una ruta más específica
public class DriverController {

    @Autowired
    private UserService usuarioService;

    @Autowired
    private UserTypeRepository tipoUsuarioRepository;
        
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<User> usuario = usuarioService.obtenerUsuarioPorId(id);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado con ID: " + id);
        }
    }

}
