package com.schoolSystem.service.admin;

import com.schoolSystem.dto.estudianteDto.EstudianteCreateDto;
import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.Estado;
import com.schoolSystem.entities.Estudiante;
import com.schoolSystem.entities.Usuario;
import com.schoolSystem.exception.CursoNotFoundException;
import com.schoolSystem.exception.UserNotFoundException;
import com.schoolSystem.repository.CursoRepository;
import com.schoolSystem.repository.EstudianteRepository;
import com.schoolSystem.repository.UsuarioRepository;
import com.schoolSystem.service.ComprobarRolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AdminEstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public AdminEstudianteService(EstudianteRepository estudianteRepository,
                                  CursoRepository cursoRepository,
                                  UsuarioRepository usuarioRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
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
                    orElseThrow(() -> new CursoNotFoundException("El curso ingresado no coincide con ninguno disponible. Compruebe los datos."));
            estudiante.addCurso(curso);
        }

        Usuario usuarioAsociado = usuarioRepository.findById(dto.id())
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe, verifique la información."));

        ComprobarRolService.comprAsignacionRolEstudiante(usuarioAsociado);

        estudiante.setUsuario(usuarioAsociado);
        estudiante.setEstado(Estado.ACTIVO);
        estudianteRepository.save(estudiante);
    }
}
