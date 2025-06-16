package com.schoolSystem.dto.estudianteDto;

import java.time.LocalDate;

public record EstudianteCreateDto(String nombre,
                                  String apellido,
                                  String numeroDocumento,
                                  LocalDate fecha_nacimiento,
                                  String direccion,
                                  String telefono,
                                  String email) {
}
