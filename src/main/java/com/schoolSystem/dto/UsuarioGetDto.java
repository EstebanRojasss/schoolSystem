package com.schoolSystem.dto;

import java.time.LocalDate;

public record UsuarioGetDto(String username, String email, LocalDate fecha_regsitro) {
}
