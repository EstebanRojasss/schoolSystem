package com.schoolSystem.service.admin;

import com.schoolSystem.dto.estudianteDto.EstudianteCreateDto;
import com.schoolSystem.dto.estudianteDto.EstudianteGetDto;
import com.schoolSystem.dto.estudianteDto.EstudianteUpdateDto;
import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.Estado;
import com.schoolSystem.entities.Estudiante;
import com.schoolSystem.entities.Usuario;
import com.schoolSystem.exception.CursoNotFoundException;
import com.schoolSystem.exception.EstudianteNotFoundException;
import com.schoolSystem.exception.UserNotFoundException;
import com.schoolSystem.mapper.estudiante.EstudianteToEstudianteGetDto;
import com.schoolSystem.mapper.estudiante.EstudianteUpdateDtoToEstudiante;
import com.schoolSystem.repository.CursoRepository;
import com.schoolSystem.repository.EstudianteRepository;
import com.schoolSystem.repository.UsuarioRepository;
import com.schoolSystem.service.ComprobarRolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminEstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstudianteUpdateDtoToEstudiante updateDtoMapper;
    private final EstudianteToEstudianteGetDto getDtoMapper;

    public AdminEstudianteService(EstudianteRepository estudianteRepository,
                                  CursoRepository cursoRepository,
                                  UsuarioRepository usuarioRepository, EstudianteUpdateDtoToEstudiante updateDtoMapper, EstudianteToEstudianteGetDto getDtoMapper) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
        this.updateDtoMapper = updateDtoMapper;
        this.getDtoMapper = getDtoMapper;
    }

    @Transactional
    public void createStudent(EstudianteCreateDto dto) {
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
    @Transactional
    public void updateStudentById(Long id, EstudianteUpdateDto updateDto) {
        Estudiante estudiante = estudianteRepository
                .findById(id)
                .orElseThrow(() -> new EstudianteNotFoundException("El estudiante no existe. Compruebe los datos."));

        updateDtoMapper.update(estudiante, updateDto);

        estudianteRepository.save(estudiante);
    }

    public Set<EstudianteGetDto> getAllStudents() {
       return estudianteRepository
               .findAll()
               .stream()
               .map(getDtoMapper::map)
               .collect(Collectors.toSet());
    }

    public void deleteStudentById(Long id){
        Estudiante estudiante = estudianteRepository
                .findById(id)
                .orElseThrow(() -> new EstudianteNotFoundException("El estudiante no existe. Compruebe los datos."));

        estudianteRepository.deleteById(id);
    }



}
