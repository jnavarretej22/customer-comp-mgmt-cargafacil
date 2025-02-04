package com.cargafacil.dto;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "parametro")
@Data
public class parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parametro_id")
    private Long parametroId;

    @Column(name = "nombre_parametro", nullable = false, length = 100)
    private String nombreParametro;

    @Column(name = "valor1", length = 500)
    private Integer valor1;

    @Column(name = "valor2", length = 500)
    private String valor2;

    @Column(name = "valor3", length = 500)
    private String valor3;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "creado_por", length = 100)
    private String creadoPor;

    @Column(name = "actualizado_por", length = 100)
    private String actualizadoPor;

    @Column(name = "estado", length = 50)
    private String estado = "activo";

    // Método para actualizar la fecha de actualización antes de guardar
    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Método para inicializar la fecha de creación al crear la entidad
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
