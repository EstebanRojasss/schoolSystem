package com.schoolSystem.controllers;

import com.schoolSystem.dto.CursoGetDto;
import com.schoolSystem.service.DocenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final DocenteService docenteService;

    public UsuarioController(DocenteService docenteService){
        this.docenteService = docenteService;
    }

    @GetMapping("/teacher")
    public ResponseEntity<Set<CursoGetDto>> getTeacherCourses(@RequestParam Long id){
        return ResponseEntity.ok( docenteService.getDocenteCursos(id) );
    }

}
