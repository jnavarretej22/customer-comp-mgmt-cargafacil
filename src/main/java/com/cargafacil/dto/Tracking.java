package com.cargafacil.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "rastreo")
@Data
@NoArgsConstructor
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rastreo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viaje_id", nullable = false)
    private Trip trip;

    @Column(name = "latitud", nullable = false)
    private Double latitud;

    @Column(name = "longitud", nullable = false)
    private Double longitud;

    @Column(name = "fecha_hora_actualizacion", nullable = false)
    private LocalDateTime fechaHoraActualizacion;
}
