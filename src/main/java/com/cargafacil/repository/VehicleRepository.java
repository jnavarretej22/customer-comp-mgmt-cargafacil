package com.cargafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cargafacil.dto.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
