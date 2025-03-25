package com.cargafacil.controller;

import com.cargafacil.dto.parametro;
import com.cargafacil.service.ParametroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parametros")
public class ParametroController {

    @Autowired
    private ParametroService parametroService;

    // Crear un nuevo parámetro
    @PostMapping
    public ResponseEntity<parametro> createParametro(@RequestBody parametro parametro) {
    	parametro createdParametro = parametroService.createParametro(parametro);
        return new ResponseEntity<>(createdParametro, HttpStatus.CREATED);
    }

    // Obtener todos los parámetros
    @GetMapping
    public ResponseEntity<List<parametro>> getAllParametros() {
        List<parametro> parametros = parametroService.getAllParametros();
        return new ResponseEntity<>(parametros, HttpStatus.OK);
    }

    // Obtener un parámetro por ID
    @GetMapping("/{id}")
    public ResponseEntity<parametro> getParametroById(@PathVariable Long id) {
        Optional<parametro> parametro = parametroService.getParametroById(id);
        return parametro.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Actualizar un parámetro
    @PutMapping("/{id}")
    public ResponseEntity<parametro> updateParametro(@PathVariable Long id, @RequestBody parametro parametro) {
    	parametro updatedParametro = parametroService.updateParametro(id, parametro);
        return updatedParametro != null ? new ResponseEntity<>(updatedParametro, HttpStatus.OK)
                                        : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar un parámetro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParametro(@PathVariable Long id) {
        parametroService.deleteParametro(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
