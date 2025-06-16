package com.schoolSystem.controllers;

import com.schoolSystem.dto.UsuarioCreateDto;
import com.schoolSystem.dto.UsuarioGetDto;
import com.schoolSystem.dto.UsuarioUpdateDto;
import com.schoolSystem.mapper.UsuarioToUsuarioDTO;
import com.schoolSystem.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController("/admin")
public class AdminController {

    private final UsuarioService usuarioService;

    private final UsuarioToUsuarioDTO mapUsuarioDto;

    public AdminController(UsuarioService usuarioService, UsuarioToUsuarioDTO mapUsuarioDto) {
        this.usuarioService = usuarioService;
        this.mapUsuarioDto = mapUsuarioDto;
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> createUser(@RequestBody UsuarioCreateDto usuarioCreateDto){
        usuarioService.createUser(usuarioCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin")
    public ResponseEntity<Set<UsuarioGetDto>>getAllUsers(){
        return ResponseEntity.ok(usuarioService.getAllUsers().
                stream().
                map(mapUsuarioDto::map).
                collect(Collectors.toSet()));
    }

    @DeleteMapping("/admin")
    public ResponseEntity<String> deleteUserByEmail(String email){
        usuarioService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id){
        usuarioService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<String>updateById(@PathVariable("id") Long id, @RequestBody UsuarioUpdateDto usuarioUpdateDto){
        usuarioService.updateById(id, usuarioUpdateDto);
        return ResponseEntity.ok().build();
    } 


}
