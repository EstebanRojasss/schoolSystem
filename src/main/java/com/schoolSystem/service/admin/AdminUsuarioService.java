package com.schoolSystem.service.admin;

import com.schoolSystem.dto.docenteDto.DocenteCreateDto;
import com.schoolSystem.dto.usuarioDto.UsuarioCreateDto;
import com.schoolSystem.dto.usuarioDto.UsuarioUpdateDto;
import com.schoolSystem.dto.estudianteDto.EstudianteCreateDto;
import com.schoolSystem.entities.*;
import com.schoolSystem.entities.rol.Rol;
import com.schoolSystem.exception.*;
import com.schoolSystem.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AdminUsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final RolRepository rolRepository;

    private final PasswordEncoder passwordEncoder;



    public AdminUsuarioService(UsuarioRepository usuarioRepository,
                               RolRepository rolRepository,
                               PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(UsuarioCreateDto usuarioCreateDto) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(usuarioCreateDto.username());
        usuario.setContrasenha(passwordEncoder.encode(usuarioCreateDto.password()));

        Optional<Usuario> email = usuarioRepository.findByEmail(usuarioCreateDto.email());
        if (email.isPresent()) {
            throw new EmailDuplicatedException("El usuario que desea ingresar no es válido o ya existe");
        }
        usuario.setEmail(usuarioCreateDto.email());

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

    public void deleteUserById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("El usuario que desea eliminar no existe, verifque la información."));
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public void updateById(Long id, UsuarioUpdateDto userUpdate) {
        Usuario usuario = usuarioRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("El usuario no existe, verifique la información"));
        actualizarDatos(usuario, userUpdate);
    }


    public void updateByEmail(String email, UsuarioUpdateDto userUpdate) {
        Usuario usuario = usuarioRepository.findByEmail(email).
                orElseThrow(() -> new UserNotFoundException("El usuario no existe, verifique la información."));
        actualizarDatos(usuario, userUpdate);
    }


    private void actualizarDatos(Usuario usuario, UsuarioUpdateDto userUpdate) {
        usuario.setNombreUsuario(userUpdate.username());
        usuario.setContrasenha(userUpdate.password());
        usuario.setEmail(userUpdate.email());
        usuario.setEstado( comprobarEstadoExistente(userUpdate.estado()) );
        usuario.setFechaRegistro(userUpdate.fecha_registro());
    }
    private Estado comprobarEstadoExistente(Estado estado){
        return Arrays.stream(Estado.values())
                .filter(es -> es.name().equalsIgnoreCase(estado.name()))
                .findFirst()
                .orElseThrow(() -> new EstadoNotFoundException("El estado ingresado no coincide con ninguno existente. Compruebe los datos."));
    }


}
