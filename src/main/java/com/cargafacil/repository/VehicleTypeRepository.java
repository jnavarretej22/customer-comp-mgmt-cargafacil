package com.cargafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cargafacil.dto.VehicleType;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
}
