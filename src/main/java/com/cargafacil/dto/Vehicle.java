package com.cargafacil.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehiculo")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehiculo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conductor_id", nullable = false)
    private User conductor;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id", nullable = false)
    private VehicleType tipoVehiculo; // Relación con la entidad `VehicleType`

    @ManyToOne
    @JoinColumn(name = "tipo_furgon_id", nullable = true)
    private BoxType tipoFurgon; // Relación con la entidad `BoxType`

    @Column(name = "capacidad_carga", nullable = false)
    private Double capacidadCarga; // En toneladas

    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @Column(name = "seguro_activo", nullable = false)
    private Boolean seguroActivo;

    @Column(name = "estado_vehiculo", nullable = false)
    private String estadoVehiculo;
}
