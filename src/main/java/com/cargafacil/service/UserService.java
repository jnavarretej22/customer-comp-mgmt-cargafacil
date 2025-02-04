package com.cargafacil.service;

import com.cargafacil.dto.User;
import com.cargafacil.dto.UserType;
import com.cargafacil.repository.UserRepository;
import com.cargafacil.repository.UserTypeRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 
    
    @Autowired
    private UserTypeRepository tipoUsuarioRepository;

    public User registrarUsuario(String nombre, String correo, String password, UserType tipoUsuario, String telefono, String direccion) {
        // Crear el nuevo usuario con el tipo de usuario
    	
        String passwordCifrada = passwordEncoder.encode(password);

        User usuario = new User(nombre, correo, passwordCifrada, tipoUsuario, telefono, direccion);
        
        // Guardar el usuario
        return usuarioRepository.save(usuario);
    }
    
 // Método para obtener un usuario por ID
    public Optional<User> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

}
