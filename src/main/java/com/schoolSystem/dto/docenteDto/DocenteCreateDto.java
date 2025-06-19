package com.schoolSystem.dto.docenteDto;

import com.schoolSystem.entities.Curso;

import java.time.LocalDate;
import java.util.Set;

public record DocenteCreateDto (String nombre,
                                String apellido,
                                String numeroDocumento,
                                LocalDate fecha_nacimiento,
                                String direccion,
                                String telefono,
                                String email,
                                Set<Curso>cursos,
                               Long id){
}
