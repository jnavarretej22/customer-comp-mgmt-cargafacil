package com.cargafacil.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    @Size(max = 100, message = "El correo no puede tener más de 100 caracteres")
    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @NotBlank(message = "La password no puede estar vacía")
    @Size(min = 8, max = 100, message = "La password debe tener entre 8 y 100 caracteres")
    @Column(name = "password", nullable = false)
    private String password;

    // Relación con la entidad TipoUsuario
    @ManyToOne
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private UserType tipoUsuario; // Relación con la tabla tipo_usuario

    @Size(max = 20, message = "El teléfono no puede tener más de 20 caracteres")
    @Column(name = "telefono")
    private String telefono;

    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
    @Column(name = "direccion")
    private String direccion;

    public User(String nombre, String correo, String password, UserType tipoUsuario, String telefono, String direccion) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}
