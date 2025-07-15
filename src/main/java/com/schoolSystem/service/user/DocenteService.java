package com.schoolSystem.service;

import com.schoolSystem.dto.cursoDto.CursoGetDto;
import com.schoolSystem.entities.Calificacion;
import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.Docente;
import com.schoolSystem.entities.Estudiante;
import com.schoolSystem.exception.CursoNotFoundException;
import com.schoolSystem.exception.DocenteNotFoundException;
import com.schoolSystem.exception.EstudianteNotFoundException;
import com.schoolSystem.mapper.curso.CursoToCursoGetDto;
import com.schoolSystem.repository.CalificacionRepository;
import com.schoolSystem.repository.DocenteRepository;
import com.schoolSystem.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final EstudianteRepository estudianteRepository;
    private final CalificacionRepository calificacionRepository;
    private final CursoToCursoGetDto cursoDtoMapper;

    public DocenteService(DocenteRepository docenteRepository,
                          EstudianteRepository estudianteRepository,
                          CalificacionRepository calificacionRepository,
                          CursoToCursoGetDto cursoDtoMapper){
        this.docenteRepository = docenteRepository;
        this.estudianteRepository = estudianteRepository;
        this.calificacionRepository = calificacionRepository;
        this.cursoDtoMapper = cursoDtoMapper;
    }

    public void setCalificacionByStudentId(Long studentId, Short nota, String nombreCurso){
        Calificacion calificacion = new Calificacion();
        Estudiante estudiante = estudianteRepository
                .findById(studentId)
                .orElseThrow(() -> new EstudianteNotFoundException("El estudiante no existe. Verifique los datos ingresados."));

        calificacion.setNota(nota);
        calificacion.setCurso( getCurso(estudiante, nombreCurso) );
        calificacion.setEstudiante(estudiante);
        calificacionRepository.save(calificacion);

    }

    public Set<CursoGetDto> getDocenteCursos(Long id){
        Docente docente = docenteRepository
                .findById(id)
                .orElseThrow(() -> new DocenteNotFoundException("El docente no existe. Verifique los datos ingresados."));
        return docente.getCursos()
                .stream()
                .map(cursoDtoMapper::map)
                .collect(Collectors.toSet());
    }


    private Curso getCurso(Estudiante estudiante, String nombreCurso){
        return estudiante.getCursos()
                .stream()
                .filter(c -> c.getNombreCurso().equalsIgnoreCase(nombreCurso))
                .findFirst()
                .orElseThrow(() -> new CursoNotFoundException("Curso no encontrado"));
    }

}
