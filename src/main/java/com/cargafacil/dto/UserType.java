package com.cargafacil.dto;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_usuario")
@Data
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_usuario_id")
    private Integer id;

    @Column(name = "nombre_tipo_usuario", nullable = false, unique = true)
    private String tipo; // puede ser "cliente", "chofer", etc.
    
    public String getTipo() {
        return tipo;
    }

}

