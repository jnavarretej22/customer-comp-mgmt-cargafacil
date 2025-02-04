package com.cargafacil.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "viaje")
@Data
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viaje_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private User cliente;

    @ManyToOne
    @JoinColumn(name = "conductor_id", nullable = true) // Cambiar nullable a true
    private User conductor; // Puede ser nulo

    // Coordenadas de origen
    @Column(name = "origen_latitud", nullable = false)
    private Double origenLatitud;

    @Column(name = "origen_longitud", nullable = false)
    private Double origenLongitud;

    // Coordenadas de destino
    @Column(name = "destino_latitud", nullable = false)
    private Double destinoLatitud;

    @Column(name = "destino_longitud", nullable = false)
    private Double destinoLongitud;

    @Column(name = "fecha_hora_carga", nullable = false)
    private LocalDateTime fechaHoraCarga;

    @Column(name = "fecha_hora_entrega", nullable = false)
    private LocalDateTime fechaHoraEntrega;

    @Column(name = "peso", nullable = false)
    private BigDecimal peso;

    @Column(name = "volumen", nullable = false)
    private BigDecimal volumen;

    @Column(name = "estado_viaje", nullable = false)
    private String estadoViaje;

    @Column(name = "costo_total", nullable = false)
    private BigDecimal costoTotal;

    // Nuevos campos
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "observacion_origen")
    private String observacionOrigen;

    @Column(name = "observacion_destino")
    private String observacionDestino;
}
