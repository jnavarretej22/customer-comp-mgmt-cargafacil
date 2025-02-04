package com.cargafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cargafacil.dto.BoxType;

public interface BoxTypeRepository extends JpaRepository<BoxType, Long> {
}
