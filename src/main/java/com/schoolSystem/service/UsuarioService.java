package com.schoolSystem.service;

import com.schoolSystem.dto.UsuarioDto;
import com.schoolSystem.entities.Estado;
import com.schoolSystem.entities.Usuario;
import com.schoolSystem.entities.rol.Rol;
import com.schoolSystem.exception.RoleNotFound;
import com.schoolSystem.exception.UserNotFoundException;
import com.schoolSystem.repository.DocenteRepository;
import com.schoolSystem.repository.EstudianteRepository;
import com.schoolSystem.repository.RolRepository;
import com.schoolSystem.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
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

    public void createUser(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario( usuarioDto.username() );
        usuario.setContrasenha( usuarioDto.password() );
        usuario.setEmail( usuarioDto.email() );

        for (Rol rol : usuarioDto.roles()) {
            Rol rolActual = rolRepository.findById(rol.getId())
                    .orElseThrow(() -> new RoleNotFound("El rol ingresado no existe"));
            usuario.addRole(rolActual);
        }
        usuario.setFechaRegistro(LocalDate.now() );
        usuario.setEstado( Estado.ACTIVO );
        usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }





}
