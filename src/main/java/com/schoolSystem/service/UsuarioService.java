package com.schoolSystem.service;

import com.schoolSystem.dto.UsuarioCreateDto;
import com.schoolSystem.dto.UsuarioGetDto;
import com.schoolSystem.entities.Estado;
import com.schoolSystem.entities.Usuario;
import com.schoolSystem.entities.rol.Rol;
import com.schoolSystem.exception.EmailException;
import com.schoolSystem.exception.RoleNotFound;
import com.schoolSystem.repository.DocenteRepository;
import com.schoolSystem.repository.EstudianteRepository;
import com.schoolSystem.repository.RolRepository;
import com.schoolSystem.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public void createUser(UsuarioCreateDto usuarioCreateDto){
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario( usuarioCreateDto.username() );
        usuario.setContrasenha( usuarioCreateDto.password() );

        usuarioRepository.findByEmail(
                usuarioCreateDto.email())
                .orElseThrow(() -> new EmailException("El email que ha ingresado no es válido o ya existe."));
            usuario.setEmail( usuarioCreateDto.email() );


        for (Rol rol : usuarioCreateDto.roles()) {
            Rol rolActual = rolRepository.findById(rol.getId())
                    .orElseThrow( () -> new RoleNotFound("El rol ingresado no existe") );
            usuario.addRole( rolActual );
        }
        usuario.setFechaRegistro( LocalDate.now() );
        usuario.setEstado( Estado.ACTIVO );
        usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }

    public void deleteUserById(Long id){
        usuarioRepository.;
    }





}
