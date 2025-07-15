package com.schoolSystem.dto.estudianteDto;

import java.util.Set;

public record EstudianteGetDto(String nombre,
                               String apellido,
                               String numeroDocumento,
                               String telefono,
                               Set<String> cursos){
}