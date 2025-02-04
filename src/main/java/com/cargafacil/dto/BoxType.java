package com.cargafacil.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tipo_furgon")
public class BoxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_furgon_id")
    private Long id;

    @Column(name = "nombre_tipo_furgon", nullable = false, unique = true)
    private String nombreTipoFurgon;

    @Column(name = "descripcion")
    private String descripcion;
}
