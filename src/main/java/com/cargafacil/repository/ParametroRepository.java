package com.cargafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cargafacil.dto.parametro;

@Repository
public interface ParametroRepository extends JpaRepository<parametro, Long> {
    // Método para encontrar por nombre_parametro
    parametro findByNombreParametro(String nombreParametro);
}