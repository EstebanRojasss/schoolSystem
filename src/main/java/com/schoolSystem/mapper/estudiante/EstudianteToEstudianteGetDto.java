package com.schoolSystem.mapper.estudiante;

import com.schoolSystem.dto.estudianteDto.EstudianteGetDto;
import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.Estudiante;
import com.schoolSystem.mapper.mapperInterfaces.IMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EstudianteToEstudianteGetDto implements IMapper<Estudiante, EstudianteGetDto> {

    @Override
    public EstudianteGetDto map(Estudiante estudiante) {
        Set<String> cursos = estudiante.getCursos()
                .stream()
                .map(Curso::getNombreCurso)
                .collect(Collectors.toSet());

        return new EstudianteGetDto(estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getNumeroDocumento(),
                estudiante.getTelefono(),
                cursos);
    }
}
