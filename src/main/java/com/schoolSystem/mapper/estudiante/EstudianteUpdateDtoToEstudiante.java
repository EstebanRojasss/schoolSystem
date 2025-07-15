package com.schoolSystem.mapper.estudiante;

import com.schoolSystem.dto.estudianteDto.EstudianteUpdateDto;
import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.Estudiante;
import com.schoolSystem.mapper.mapperInterfaces.IUpdate;
import org.springframework.stereotype.Component;

@Component
public class EstudianteUpdateDtoToEstudiante implements IUpdate<Estudiante, EstudianteUpdateDto> {

    @Override
    public void update(Estudiante estudiante, EstudianteUpdateDto estudianteUpdateDto, Curso curso) {

    }
    public void update(Estudiante estudiante, EstudianteUpdateDto updateDto) {
        estudiante.setNombre(updateDto.nombre());
        estudiante.setApellido(updateDto.apellido());
        estudiante.setDireccion(updateDto.direccion());
        estudiante.setTelefono(updateDto.telefono());
    }
}
