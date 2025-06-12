package com.schoolSystem.service;

import com.schoolSystem.entities.Estudiante;
import com.schoolSystem.entities.Usuario;
import com.schoolSystem.entities.rol.Rol;
import com.schoolSystem.repository.DocenteRepository;
import com.schoolSystem.repository.EstudianteRepository;
import com.schoolSystem.repository.RolRepository;
import com.schoolSystem.repository.UsuarioRepository;

import java.util.Optional;

public class UsuarioService {

    private final  UsuarioRepository usuarioRepository;

    private final RolRepository rolRepository;

    private final EstudianteRepository estudianteRepository;

    private final DocenteRepository docenteRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, EstudianteRepository estudianteRepository, DocenteRepository docenteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.estudianteRepository = estudianteRepository;
        this.docenteRepository = docenteRepository;
    }

    public void matchUser(Usuario usuario, Rol rol){
        
    }

}
