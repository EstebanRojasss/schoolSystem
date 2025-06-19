package com.schoolSystem.controllers;

import com.schoolSystem.dto.docenteDto.DocenteCreateDto;
import com.schoolSystem.dto.usuarioDto.UsuarioCreateDto;
import com.schoolSystem.dto.usuarioDto.UsuarioGetDto;
import com.schoolSystem.dto.usuarioDto.UsuarioUpdateDto;
import com.schoolSystem.dto.estudianteDto.EstudianteCreateDto;
import com.schoolSystem.mapper.UsuarioToUsuarioDTO;
import com.schoolSystem.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/admin")
public class AdminController {

    private final UsuarioService usuarioService;

    private final UsuarioToUsuarioDTO mapUsuarioDto;

    public AdminController(UsuarioService usuarioService, UsuarioToUsuarioDTO mapUsuarioDto) {
        this.usuarioService = usuarioService;
        this.mapUsuarioDto = mapUsuarioDto;
    }

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody UsuarioCreateDto usuarioCreateDto){
        usuarioService.createUser(usuarioCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<Set<UsuarioGetDto>>getAllUsers(){
        return ResponseEntity.ok(usuarioService.getAllUsers().
                stream().
                map(mapUsuarioDto::map).
                collect(Collectors.toSet()));
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUserByEmail(String email){
        usuarioService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id){
        usuarioService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("user/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable("id") Long id, @RequestBody UsuarioUpdateDto usuarioUpdateDto){
        usuarioService.updateById(id, usuarioUpdateDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/student")
    public ResponseEntity<String> createStudent(@RequestBody EstudianteCreateDto estudianteCreateDto){
        usuarioService.crearEstudiante(estudianteCreateDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/teacher")
    public ResponseEntity<String> createTeacher(@RequestBody DocenteCreateDto docenteCreateDto){
        usuarioService.crearDocente(docenteCreateDto);
        return ResponseEntity.ok().build();
    }


}
