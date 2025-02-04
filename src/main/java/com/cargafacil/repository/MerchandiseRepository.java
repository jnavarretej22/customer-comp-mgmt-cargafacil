package com.cargafacil.repository;

import com.cargafacil.dto.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
    // Métodos personalizados si los necesitas
}
