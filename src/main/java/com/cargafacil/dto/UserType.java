package com.cargafacil.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tipo_usuario")
@Data
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_usuario_id")
    private Integer id;

    @Column(name = "nombre_tipo_usuario", nullable = false)
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "estado", nullable = false)
    private String state = "activo";

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime updatedAt;

}
