package com.schoolSystem.dto.usuarioDto;

import com.schoolSystem.entities.rol.Rol;

import java.util.Set;

public record UsuarioCreateDto(String username, String password, String email, Set<Rol> roles){
}
