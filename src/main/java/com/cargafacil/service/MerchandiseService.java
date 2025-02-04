package com.cargafacil.service;

import com.cargafacil.dto.Merchandise;
import com.cargafacil.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchandiseService {

    @Autowired
    private MerchandiseRepository merchandiseRepository;

    public Merchandise createMerchandise(Merchandise merchandise) {
        return merchandiseRepository.save(merchandise);
    }

    public List<Merchandise> getAllMerchandise() {
        return merchandiseRepository.findAll();
    }

    public Optional<Merchandise> getMerchandiseById(Long id) {
        return merchandiseRepository.findById(id);
    }

    public Merchandise updateMerchandise(Long id, Merchandise updatedMerchandise) {
        return merchandiseRepository.findById(id).map(existingMerchandise -> {
            existingMerchandise.setTipoMercancia(updatedMerchandise.getTipoMercancia());
            existingMerchandise.setVolumen(updatedMerchandise.getVolumen());
            existingMerchandise.setPeso(updatedMerchandise.getPeso());
            existingMerchandise.setEstibas(updatedMerchandise.getEstibas());
            existingMerchandise.setSeguro(updatedMerchandise.getSeguro());
            existingMerchandise.setDescripcion(updatedMerchandise.getDescripcion());
            return merchandiseRepository.save(existingMerchandise);
        }).orElseThrow(() -> new RuntimeException("Merchandise not found with ID " + id));
    }

    public void deleteMerchandise(Long id) {
        merchandiseRepository.deleteById(id);
    }
}
