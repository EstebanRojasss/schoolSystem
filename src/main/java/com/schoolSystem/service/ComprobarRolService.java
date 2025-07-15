package com.schoolSystem.service;

import com.schoolSystem.entities.Usuario;
import com.schoolSystem.entities.rol.Rol;
import com.schoolSystem.exception.IncorrectRoleException;
import com.schoolSystem.exception.RoleNotFound;
import com.schoolSystem.repository.RolRepository;
import org.springframework.stereotype.Service;

@Service
public class ComprobarRolService {

    private static RolRepository rolRepository;

    public ComprobarRolService(RolRepository rolRepository) {
        ComprobarRolService.rolRepository = rolRepository;
    }

    private static void comprobacionRolExistente(Usuario usuario) {
        for (Rol rol : usuario.getRoles()) {
            rolRepository.findById(rol.getId())
                    .orElseThrow(() -> new RoleNotFound("El rol ingresado no coincide con ninguno disponible. Verifique los datos"));
        }
    }

    public static void comprAsignacionRolEstudiante(Usuario usuario) {
        comprobacionRolExistente(usuario);
        if (usuario.getRoles().stream().noneMatch(rol -> rol.getTipoRol().toString().equals("ESTUDIANTE"))) {
            throw new IncorrectRoleException("El usuario especificado no tiene rol de ESTUDIANTE");
        }
    }

    public static void comprAsignacionRolDocente(Usuario usuario) {
        comprobacionRolExistente(usuario);
        if (usuario.getRoles().stream().noneMatch(rol -> rol.getTipoRol().toString().equals("DOCENTE"))) {
            throw new IncorrectRoleException("El usuario especificado no tiene rol de DOCENTE");
        }
    }
}
