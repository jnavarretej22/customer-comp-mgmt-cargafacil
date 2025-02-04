package com.cargafacil.service;

import com.cargafacil.dto.Trip;
import com.cargafacil.dto.User;
import com.cargafacil.repository.TripRepository;
import com.cargafacil.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;
    
    @Autowired
    private UserRepository userRepository;

    public Trip createTrip(Trip trip) {
        // Verificar si el cliente existe
        User cliente = userRepository.findById(trip.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con el ID: " + trip.getCliente().getId()));

        trip.setCliente(cliente);

        // Verificar si el conductor existe (opcional)
        if (trip.getConductor() != null && trip.getConductor().getId() != null) {
            User conductor = userRepository.findById(trip.getConductor().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Conductor no encontrado con el ID: " + trip.getConductor().getId()));
            trip.setConductor(conductor);
        } else {
            trip.setConductor(null); // Asegurar que el conductor sea explícitamente nulo si no se proporciona
        }

        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }



    public Trip updateTrip(Long id, Trip updatedTrip) {
        return tripRepository.findById(id).map(existingTrip -> {
            existingTrip.setCliente(updatedTrip.getCliente());
            existingTrip.setConductor(updatedTrip.getConductor());
            existingTrip.setOrigenLatitud(updatedTrip.getOrigenLatitud());
            existingTrip.setOrigenLongitud(updatedTrip.getOrigenLongitud());
            existingTrip.setDestinoLatitud(updatedTrip.getDestinoLatitud());
            existingTrip.setDestinoLongitud(updatedTrip.getDestinoLongitud());
            existingTrip.setFechaHoraCarga(updatedTrip.getFechaHoraCarga());
            existingTrip.setFechaHoraEntrega(updatedTrip.getFechaHoraEntrega());
            existingTrip.setPeso(updatedTrip.getPeso());
            existingTrip.setVolumen(updatedTrip.getVolumen());
            existingTrip.setEstadoViaje(updatedTrip.getEstadoViaje());
            existingTrip.setCostoTotal(updatedTrip.getCostoTotal());
            existingTrip.setDescripcion(updatedTrip.getDescripcion());
            existingTrip.setObservacionOrigen(updatedTrip.getObservacionOrigen());
            existingTrip.setObservacionDestino(updatedTrip.getObservacionDestino());
            return tripRepository.save(existingTrip);
        }).orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
}
