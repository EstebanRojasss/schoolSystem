package com.schoolSystem.service;

import com.schoolSystem.dto.DocenteCreateDto;
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
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final RolRepository rolRepository;

    private final EstudianteRepository estudianteRepository;

    private final DocenteRepository docenteRepository;

    private final PasswordEncoder passwordEncoder;

    private final CursoRepository cursoRepository;


    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository,
                          EstudianteRepository estudianteRepository, DocenteRepository docenteRepository,
                          PasswordEncoder passwordEncoder, CursoRepository cursoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.estudianteRepository = estudianteRepository;
        this.docenteRepository = docenteRepository;
        this.passwordEncoder = passwordEncoder;
        this.cursoRepository = cursoRepository;
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

    @Transactional
    public void crearEstudiante(EstudianteCreateDto dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.nombre());
        estudiante.setApellido(dto.apellido());
        estudiante.setNumeroDocumento(dto.numeroDocumento());
        estudiante.setFechaNacimiento(dto.fecha_nacimiento());
        estudiante.setFechaNacimiento(LocalDate.now());
        estudiante.setDireccion(dto.direccion());
        estudiante.setTelefono(dto.telefono());
        estudiante.setEmail(dto.email());

        for (Curso comprobarCurso : dto.cursos()) {
            Curso curso = cursoRepository.findById(comprobarCurso.getId()).
                    orElseThrow(() -> new CursoNotFoundException("El curso no existe."));
            estudiante.addCurso(curso);
        }

        Usuario usuarioAsociado = usuarioRepository.findById(dto.id())
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe, verifique la información."));

        comprAsignacionRolEstudiante(usuarioAsociado);

        estudiante.setUsuario(usuarioAsociado);
        estudiante.setEstado(Estado.ACTIVO);
        estudianteRepository.save(estudiante);
    }

    @Transactional
    public void crearDocente(DocenteCreateDto dto){
        Docente docente = new Docente();
        docente.setNombre(dto.nombre());
        docente.setApellido(dto.apellido());
        docente.setNumeroDocumento(dto.numeroDocumento());
        docente.setFechaNacimiento(dto.fecha_nacimiento());
        docente.setTelefono(dto.telefono());
        docente.setDireccion(dto.direccion());
        docente.setEmail(dto.email());

        for(Curso curso : dto.cursos()){
            Curso comprobarCurso = cursoRepository.findById(curso.getId())
                    .orElseThrow(() -> new CursoNotFoundException("El curso ingresado no coincide con ninguno disponible. Compruebe los datos.") );
            docente.addCurso(comprobarCurso);
        }
        Usuario usuario = usuarioRepository.findById(dto.id())
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe. Verifique la información."));

        comprAsignacionRolDocente(usuario);

        docente.setUsuario(usuario);
        docente.setEstado(Estado.ACTIVO);
        docenteRepository.save(docente);
    }

    private void comprobacionRolExistente(Usuario usuario) {
        for (Rol rol : usuario.getRoles()) {
            rolRepository.findById(rol.getId())
                    .orElseThrow(() -> new RoleNotFound("El rol ingresado no coincide con ninguno disponible. Verifique los datos"));
        }
    }

    private void comprAsignacionRolEstudiante(Usuario usuario) {
        comprobacionRolExistente(usuario);
        if (usuario.getRoles().stream().noneMatch(rol -> rol.getTipoRol().toString().equals("ESTUDIANTE"))) {
            throw new IncorrectRoleException("El usuario especificado no tiene rol de ESTUDIANTE");
        }
    }

    private void comprAsignacionRolDocente(Usuario usuario) {
        comprobacionRolExistente(usuario);
        if (usuario.getRoles().stream().noneMatch(rol -> rol.getTipoRol().toString().equals("DOCENTE"))) {
            throw new IncorrectRoleException("El usuario especificado no tiene rol de DOCENTE");
        }
    }

    private Estado comprobarEstadoExistente(Estado estado){
        return Arrays.stream(Estado.values())
                .filter(es -> es.name().equalsIgnoreCase(estado.name()))
                .findFirst()
                .orElseThrow(() -> new EstadoNotFoundException("El estado ingresado no coincide con ninguno existente. Compruebe los datos."));
    }


}
