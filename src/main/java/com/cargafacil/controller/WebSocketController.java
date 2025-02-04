package com.cargafacil.controller;

import com.cargafacil.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private TrackingService trackingService;

    // Endpoint para recibir actualizaciones del cliente
    @MessageMapping("/update-location")
    public void updateLocation(Long tripId, Double latitud, Double longitud) {
        trackingService.sendLocationUpdate(tripId, latitud, longitud);
    }
}
