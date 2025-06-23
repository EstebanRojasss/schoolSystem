package com.schoolSystem.mapper;

import com.schoolSystem.dto.CursoGetDto;
import com.schoolSystem.entities.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoToCursoGetDto implements IMapper<Curso, CursoGetDto> {

    @Override
    public CursoGetDto map(Curso in) {
        return new CursoGetDto( in.getNombreCurso(), in.getCreditos() );
    }
}
