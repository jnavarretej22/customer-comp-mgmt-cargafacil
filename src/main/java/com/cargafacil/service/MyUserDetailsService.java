package com.cargafacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cargafacil.dto.MyUserDetails;
import com.cargafacil.dto.User;
import com.cargafacil.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        User usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new MyUserDetails(usuario);
    }
    
    
    public String obtenerRolPorCorreo(String correo) {
        User usuario = usuarioRepository.findByCorreo(correo);

        // Verificar que el usuario existe
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Retornar el rol (suponiendo que el campo es 'tipoUsuario')
        return usuario.getTipoUsuario().getTipo();  // Asegúrate de que 'tipoUsuario' es el nombre del campo que contiene el rol
    }

}