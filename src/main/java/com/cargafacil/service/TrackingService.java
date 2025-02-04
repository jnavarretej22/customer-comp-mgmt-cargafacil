package com.cargafacil.service;

import com.cargafacil.dto.Tracking;
import com.cargafacil.dto.Trip;
import com.cargafacil.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository trackingRepository;

    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendLocationUpdate(Long tripId, Double latitud, Double longitud) {
        Map<String, Double> location = Map.of(
                "latitud", latitud,
                "longitud", longitud
        );
        messagingTemplate.convertAndSend("/topic/trip/" + tripId, location);
    }
    
    // Registrar una nueva ubicación
    public Tracking saveTracking(Long tripId, Double latitud, Double longitud) {
        Tracking tracking = new Tracking();
        tracking.setLatitud(latitud);
        tracking.setLongitud(longitud);
        tracking.setFechaHoraActualizacion(LocalDateTime.now());

        // Vincular con el viaje
        tracking.setTrip(new Trip());
        tracking.getTrip().setId(tripId);

        return trackingRepository.save(tracking);
    }

    // Obtener historial de ubicaciones de un viaje
    public List<Tracking> getTrackingHistory(Long tripId) {
        return trackingRepository.findByTripIdOrderByFechaHoraActualizacionAsc(tripId);
    }
}
