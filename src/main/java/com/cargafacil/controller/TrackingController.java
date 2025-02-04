package com.cargafacil.controller;

import com.cargafacil.dto.Tracking;
import com.cargafacil.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    // Endpoint para registrar una ubicación
    @PostMapping("/{tripId}")
    public ResponseEntity<Tracking> saveTracking(
            @PathVariable Long tripId,
            @RequestParam Double latitud,
            @RequestParam Double longitud
    ) {
        Tracking tracking = trackingService.saveTracking(tripId, latitud, longitud);
        return ResponseEntity.ok(tracking);
    }

    // Endpoint para obtener el historial de rastreo de un viaje
    @GetMapping("/{tripId}")
    public ResponseEntity<List<Tracking>> getTrackingHistory(@PathVariable Long tripId) {
        List<Tracking> trackingHistory = trackingService.getTrackingHistory(tripId);
        return ResponseEntity.ok(trackingHistory);
    }
}
