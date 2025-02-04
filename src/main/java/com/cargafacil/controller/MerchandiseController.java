package com.cargafacil.controller;

import com.cargafacil.dto.Merchandise;
import com.cargafacil.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {

    @Autowired
    private MerchandiseService merchandiseService;

    @PostMapping
    public ResponseEntity<Merchandise> createMerchandise(@RequestBody Merchandise merchandise) {
        return ResponseEntity.ok(merchandiseService.createMerchandise(merchandise));
    }

    @GetMapping
    public ResponseEntity<List<Merchandise>> getAllMerchandise() {
        return ResponseEntity.ok(merchandiseService.getAllMerchandise());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchandise> getMerchandiseById(@PathVariable Long id) {
        return merchandiseService.getMerchandiseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchandise> updateMerchandise(@PathVariable Long id, @RequestBody Merchandise merchandise) {
        return ResponseEntity.ok(merchandiseService.updateMerchandise(id, merchandise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchandise(@PathVariable Long id) {
        merchandiseService.deleteMerchandise(id);
        return ResponseEntity.noContent().build();
    }
}
