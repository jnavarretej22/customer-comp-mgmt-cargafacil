package com.cargafacil.repository;

import com.cargafacil.dto.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
    // Puedes agregar consultas personalizadas si es necesario
}
