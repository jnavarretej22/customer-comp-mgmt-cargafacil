package com.cargafacil.service;

import com.cargafacil.dto.UserType;
import com.cargafacil.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    // Obtener todos los tipos de usuario
    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    // Obtener un tipo de usuario por su ID
    public Optional<UserType> getUserTypeById(Integer id) {
        return userTypeRepository.findById(id);
    }

    // Crear un nuevo tipo de usuario
    public UserType createUserType(UserType userType) {
        userType.setCreatedAt(LocalDateTime.now());
        userType.setUpdatedAt(LocalDateTime.now());
        return userTypeRepository.save(userType);
    }

    // Actualizar un tipo de usuario
    public UserType updateUserType(Integer id, UserType updatedUserType) {
        return userTypeRepository.findById(id).map(userType -> {
            userType.setName(updatedUserType.getName());
            userType.setDescription(updatedUserType.getDescription());
            userType.setState(updatedUserType.getState());
            userType.setUpdatedAt(LocalDateTime.now());
            return userTypeRepository.save(userType);
        }).orElseThrow(() -> new RuntimeException("UserType not found with id " + id));
    }

    // Eliminar un tipo de usuario
    public void deleteUserType(Integer id) {
        userTypeRepository.deleteById(id);
    }
}
