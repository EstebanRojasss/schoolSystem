package com.schoolSystem.dto.usuarioDto;

import java.time.LocalDate;

public record UsuarioGetDto(String username, String email, LocalDate fecha_regsitro) {
}
