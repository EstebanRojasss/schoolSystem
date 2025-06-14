package com.schoolSystem.service;

import com.schoolSystem.dto.UsuarioCreateDto;
import com.schoolSystem.entities.Estado;
import com.schoolSystem.entities.Usuario;
import com.schoolSystem.entities.rol.Rol;
import com.schoolSystem.exception.EmailDuplicatedException;
import com.schoolSystem.exception.RoleNotFound;
import com.schoolSystem.repository.DocenteRepository;
import com.schoolSystem.repository.EstudianteRepository;
import com.schoolSystem.repository.RolRepository;
import com.schoolSystem.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final RolRepository rolRepository;

    private final EstudianteRepository estudianteRepository;

    private final DocenteRepository docenteRepository;

    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository,
                          EstudianteRepository estudianteRepository, DocenteRepository docenteRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.estudianteRepository = estudianteRepository;
        this.docenteRepository = docenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UsuarioCreateDto usuarioCreateDto) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(usuarioCreateDto.username());
        usuario.setContrasenha(passwordEncoder.encode(usuarioCreateDto.password()));

        Optional<Usuario> email = usuarioRepository.findByEmail( usuarioCreateDto.email() );
        if(email.isPresent()){
            throw new EmailDuplicatedException( "El usuario que desea ingresar no es válido o ya existe" );
        }
        usuario.setEmail( usuarioCreateDto.email() );

        for (Rol rol : usuarioCreateDto.roles()) {
            Rol rolActual = rolRepository.findById(rol.getId())
                    .orElseThrow(() -> new RoleNotFound("El rol ingresado no existe"));
            usuario.addRole(rolActual);
        }
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setEstado(Estado.ACTIVO);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public void deleteUserByEmail(String email) {
        Usuario usuarioABorrar = usuarioRepository
                .findByEmail(email)
                .orElseThrow(() -> new EmailDuplicatedException("El usuario que desea eliminar no existe, verifique la información."));
        usuarioRepository.delete(usuarioABorrar);
    }


}
