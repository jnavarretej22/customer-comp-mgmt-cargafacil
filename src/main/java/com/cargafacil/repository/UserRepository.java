package com.cargafacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cargafacil.dto.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByCorreo(String correo);
    
    @EntityGraph(attributePaths = {"tipoUsuario"})
    Optional<User> findById(Long id);
}