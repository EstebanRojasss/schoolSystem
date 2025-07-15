package com.schoolSystem.mapper.docente;

import com.schoolSystem.dto.docenteDto.DocenteUpdateDto;
import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.Docente;
import com.schoolSystem.mapper.mapperInterfaces.IUpdate;

public class DocenteUpdateDtoToDocente implements IUpdate<Docente, DocenteUpdateDto> {

    @Override
    public void update(Docente docente, DocenteUpdateDto inputDto, Curso curso) {
        docente.setNombre(inputDto.nombre());
        docente.setApellido(inputDto.apellido());
        docente.setNumeroDocumento(inputDto.numeroDocumento());
        docente.addCurso(curso);
    }
}
