package com.schoolSystem.controllers;

import com.schoolSystem.dto.UsuarioDto;
import com.schoolSystem.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UsuarioDto usuarioDto){
        usuarioService.createUser(usuarioDto);
        return ResponseEntity.ok().build();
    }


}
