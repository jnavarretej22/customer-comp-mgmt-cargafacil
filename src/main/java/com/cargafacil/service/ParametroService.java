package com.cargafacil.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cargafacil.dto.parametro;
import com.cargafacil.repository.ParametroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParametroService {

    @Autowired
    private ParametroRepository parametroRepository;

    // Método para obtener parámetros por nombre
    public parametro obtenerPorNombreParametro(String nombreParametro) {
        return parametroRepository.findByNombreParametro(nombreParametro);
    }
    
    
 // Crear un nuevo parámetro
    public parametro createParametro(parametro parametro) {
        return parametroRepository.save(parametro);
    }

    // Obtener todos los parámetros
    public List<parametro> getAllParametros() {
        return parametroRepository.findAll();
    }

    // Obtener un parámetro por su ID
    public Optional<parametro> getParametroById(Long id) {
        return parametroRepository.findById(id);
    }

    // Actualizar un parámetro
    public parametro updateParametro(Long id, parametro parametro) {
        if (parametroRepository.existsById(id)) {
            parametro.setParametroId(id);
            return parametroRepository.save(parametro);
        }
        return null;
    }

    // Eliminar un parámetro
    public void deleteParametro(Long id) {
        parametroRepository.deleteById(id);
    }
}
