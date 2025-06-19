package com.schoolSystem.dto;

import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.Usuario;

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
