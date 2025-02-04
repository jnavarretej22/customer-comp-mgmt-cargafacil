package com.cargafacil.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "mercancia")
@Data
@NoArgsConstructor
public class Merchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mercancia_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viaje_id", nullable = false)
    private Trip trip; // Relación con el viaje

    @Column(name = "tipo_mercancia", nullable = false)
    private String tipoMercancia;

    @Column(name = "volumen", nullable = false)
    private BigDecimal volumen; // En metros cúbicos

    @Column(name = "peso", nullable = false)
    private BigDecimal peso; // En toneladas

    @Column(name = "estibas", nullable = false)
    private Integer estibas; // Número de estibas utilizadas

    @Column(name = "seguro", nullable = false)
    private Boolean seguro; // Indica si tiene seguro

    @Column(name = "descripcion")
    private String descripcion; // Descripción adicional
}
