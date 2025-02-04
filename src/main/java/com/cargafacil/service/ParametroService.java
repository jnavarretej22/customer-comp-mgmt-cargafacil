package com.cargafacil.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cargafacil.dto.parametro;
import com.cargafacil.repository.ParametroRepository;

import java.util.List;

@Service
public class ParametroService {

    @Autowired
    private ParametroRepository parametroRepository;

    // Método para obtener parámetros por nombre
    public parametro obtenerPorNombreParametro(String nombreParametro) {
        return parametroRepository.findByNombreParametro(nombreParametro);
    }
}
