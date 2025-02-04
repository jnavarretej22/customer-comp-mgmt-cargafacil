package com.cargafacil.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tipo_vehiculo")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_vehiculo_id")
    private Long id;

    @Column(name = "nombre_tipo_vehiculo", nullable = false, unique = true)
    private String nombreTipoVehiculo;

    @Column(name = "descripcion")
    private String descripcion;
}
