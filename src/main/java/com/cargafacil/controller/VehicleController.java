package com.cargafacil.controller;

import com.cargafacil.dto.Vehicle;
import com.cargafacil.service.VehicleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
    private VehicleService vehicleService;

    // Crear un vehículo
    @PostMapping("/create")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicleData) {
        // Crear el vehículo
        Vehicle createdVehicle = vehicleService.createVehicle(vehicleData);

        // Responder con el vehículo creado
        return ResponseEntity.ok(createdVehicle);
    }

    // Actualizar un vehículo
    @PutMapping("/update/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        return vehicleService.getVehicleById(id).map(vehicle -> {
            vehicle.setConductor(vehicleDetails.getConductor());
            vehicle.setTipoVehiculo(vehicleDetails.getTipoVehiculo());
            vehicle.setTipoFurgon(vehicleDetails.getTipoFurgon());
            vehicle.setCapacidadCarga(vehicleDetails.getCapacidadCarga());
            vehicle.setPlaca(vehicleDetails.getPlaca());
            vehicle.setSeguroActivo(vehicleDetails.getSeguroActivo());
            vehicle.setEstadoVehiculo(vehicleDetails.getEstadoVehiculo());
            Vehicle updatedVehicle = vehicleService.saveVehicle(vehicle);
            return ResponseEntity.ok(updatedVehicle);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Obtener todos los vehículos
    @GetMapping("/list")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    // Obtener un vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un vehículo por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Long id) {
        if (vehicleService.getVehicleById(id).isPresent()) {
            vehicleService.deleteVehicleById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
