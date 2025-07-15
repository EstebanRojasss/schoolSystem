package com.schoolSystem.mapper.docente;

import com.schoolSystem.dto.docenteDto.DocenteGetDto;
import com.schoolSystem.entities.Docente;
import com.schoolSystem.mapper.mapperInterfaces.IMapper;

public class DocenteToDocenteGetDto implements IMapper<Docente, DocenteGetDto> {

    @Override
    public DocenteGetDto map(Docente in) {
        return new DocenteGetDto(in.getNombre(),
                in.getApellido(),
                in.getTelefono(),
                in.getDireccion());
    }
}
