package com.cargafacil.repository;

import com.cargafacil.dto.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {

    // Obtener el historial de rastreo de un viaje
    List<Tracking> findByTripIdOrderByFechaHoraActualizacionAsc(Long tripId);
}
