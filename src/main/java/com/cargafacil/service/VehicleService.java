package com.cargafacil.service;

import com.cargafacil.dto.BoxType;
import com.cargafacil.dto.User;
import com.cargafacil.dto.Vehicle;
import com.cargafacil.dto.VehicleType;
import com.cargafacil.repository.BoxTypeRepository;
import com.cargafacil.repository.UserRepository;
import com.cargafacil.repository.VehicleRepository;
import com.cargafacil.repository.VehicleTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
   
    @Autowired
    private UserRepository driverRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private BoxTypeRepository boxTypeRepository;

    public Vehicle createVehicle(Vehicle vehicleData) {
        // Validar y obtener el conductor
        User conductor = driverRepository.findById(vehicleData.getConductor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Conductor no encontrado"));

        // Validar y obtener el tipo de vehículo
        VehicleType tipoVehiculo = vehicleTypeRepository.findById(vehicleData.getTipoVehiculo().getId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de vehículo no encontrado"));

        // Validar y obtener el tipo de furgón
        BoxType tipoFurgon = boxTypeRepository.findById(vehicleData.getTipoFurgon().getId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de furgón no encontrado"));

        // Asignar relaciones al vehículo
        vehicleData.setConductor(conductor);
        vehicleData.setTipoVehiculo(tipoVehiculo);
        vehicleData.setTipoFurgon(tipoFurgon);

        // Guardar el vehículo
        return vehicleRepository.save(vehicleData);
    }

    // Crear o actualizar un vehículo
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Obtener todos los vehículos
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Obtener un vehículo por ID
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    // Eliminar un vehículo por ID
    public void deleteVehicleById(Long id) {
        vehicleRepository.deleteById(id);
    }
}
