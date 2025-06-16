package com.schoolSystem.dto.estudianteDto;

import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.Usuario;

import java.time.LocalDate;
import java.util.Set;

public record EstudianteCreateDto(String nombre,
                                  String apellido,
                                  String numeroDocumento,
                                  LocalDate fecha_nacimiento,
                                  String direccion,
                                  String telefono,
                                  String email,
                                  Set<Curso>cursos,
                                  Long id) {
}
