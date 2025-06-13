package com.schoolSystem.service;

import com.schoolSystem.entities.Usuario;
import com.schoolSystem.entities.rol.Rol;
import com.schoolSystem.exception.RoleNotFound;
import com.schoolSystem.exception.UserNotFoundException;
import com.schoolSystem.repository.DocenteRepository;
import com.schoolSystem.repository.EstudianteRepository;
import com.schoolSystem.repository.RolRepository;
import com.schoolSystem.repository.UsuarioRepository;

import java.util.HashSet;
import java.util.Set;


public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final RolRepository rolRepository;

    private final EstudianteRepository estudianteRepository;

    private final DocenteRepository docenteRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository,
                          EstudianteRepository estudianteRepository, DocenteRepository docenteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.estudianteRepository = estudianteRepository;
        this.docenteRepository = docenteRepository;
    }

    public void matchUser(Usuario usuario, Set<Rol> roles) {
        Usuario usuarioActual = usuarioRepository.findById(usuario.getId())
                .orElseThrow( () -> new UserNotFoundException("El usuario ingresado no existe"));

        Set<Rol> addRoles = new HashSet<>();

        for (Rol rol : roles) {
            Rol rolActual = rolRepository.findById(rol.getId())
                    .orElseThrow(() -> new RoleNotFound("El rol ingresado no existe"));
            addRoles.add(rolActual);
        }

        usuarioActual.setRoles(addRoles);
    }

}
