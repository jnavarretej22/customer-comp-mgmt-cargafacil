package com.cargafacil.repository;

import com.cargafacil.dto.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
