package com.schoolSystem.dto;

import com.schoolSystem.entities.Estado;

import java.time.LocalDate;

public record UsuarioUpdateDto (String username, String password, LocalDate fecha_registro, Estado estado, String email){
}
