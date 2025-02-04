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
@RequestMapping("/user") // Definimos una ruta más específica
public class UserController {

    @Autowired
    private UserService usuarioService;

    @Autowired
    private UserTypeRepository tipoUsuarioRepository;
    
    @PostMapping("/register")
    public ResponseEntity<User> registrarUsuario(@RequestBody User usuarioDTO) {
        // Verificamos si el tipo de usuario proporcionado es válido
        if (usuarioDTO.getTipoUsuario() == null) {
            return ResponseEntity.badRequest().body(null); // Si el tipo de usuario es nulo, respondemos con error
        }

        // Buscar el tipo de usuario por ID
        UserType tipoUsuario = tipoUsuarioRepository.findById(usuarioDTO.getTipoUsuario().getId())
            .orElseThrow(() -> new IllegalArgumentException("Tipo de usuario no válido"));

        // Llamar al servicio con el tipo de usuario completo
        User usuario = usuarioService.registrarUsuario(
            usuarioDTO.getNombre(),
            usuarioDTO.getCorreo(),
            usuarioDTO.getPassword(),
            tipoUsuario, // Enviar el objeto TipoUsuario completo
            usuarioDTO.getTelefono(),
            usuarioDTO.getDireccion()
        );

        return ResponseEntity.ok(usuario);
    }
    
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
