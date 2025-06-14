package com.schoolSystem.controllers;

import com.schoolSystem.dto.UsuarioCreateDto;
import com.schoolSystem.dto.UsuarioGetDto;
import com.schoolSystem.mapper.UsuarioToUsuarioDTO;
import com.schoolSystem.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController("/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioToUsuarioDTO mapper;

    public UsuarioController(UsuarioService usuarioService, UsuarioToUsuarioDTO mapper) {
        this.usuarioService = usuarioService;
        this.mapper = mapper;
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> createUser(@RequestBody UsuarioCreateDto usuarioCreateDto){
        usuarioService.createUser(usuarioCreateDto);
        return ResponseEntity.ok().build();
    }

    


}
