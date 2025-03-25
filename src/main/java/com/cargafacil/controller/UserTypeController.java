package com.cargafacil.controller;

import com.cargafacil.dto.UserType;
import com.cargafacil.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-types")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    // Obtener todos los tipos de usuario
    @GetMapping
    public List<UserType> getAllUserTypes() {
        return userTypeService.getAllUserTypes();
    }

    // Obtener un tipo de usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserType> getUserTypeById(@PathVariable Integer id) {
        Optional<UserType> userType = userTypeService.getUserTypeById(id);
        return userType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo tipo de usuario
    @PostMapping
    public ResponseEntity<UserType> createUserType(@RequestBody UserType userType) {
        UserType createdUserType = userTypeService.createUserType(userType);
        return ResponseEntity.status(201).body(createdUserType);
    }

    // Actualizar un tipo de usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserType> updateUserType(@PathVariable Integer id, @RequestBody UserType userType) {
        UserType updatedUserType = userTypeService.updateUserType(id, userType);
        return ResponseEntity.ok(updatedUserType);
    }

    // Eliminar un tipo de usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable Integer id) {
        userTypeService.deleteUserType(id);
        return ResponseEntity.noContent().build();
    }
}
