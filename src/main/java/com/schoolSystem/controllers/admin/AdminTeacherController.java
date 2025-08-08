package com.schoolSystem.controllers.admin;

import com.schoolSystem.dto.docenteDto.DocenteCreateDto;
import com.schoolSystem.dto.docenteDto.DocenteGetDto;
import com.schoolSystem.dto.docenteDto.DocenteUpdateDto;
import com.schoolSystem.service.admin.AdminDocenteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminTeacherController {

    private final AdminDocenteService teacherService;

    public AdminTeacherController(AdminDocenteService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/teacher")
    @Operation(summary = "Crea nuevo profesor.",
    description = "Endpoint encargado de crear nuevo profesor.")
    public ResponseEntity<String>createTeacher(@RequestBody DocenteCreateDto createDto){
        teacherService.createTeacher(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("teacher/{id}")
    @Operation(summary = "Actualiza profesor de acuerdo al Id ingresado",
    description = "Endpoint encargado de actualizar los datos de un profesor.")
    public ResponseEntity<String>updateTeacherById(@RequestBody DocenteUpdateDto updateDto, @PathVariable Long id){
        teacherService.updateTeacherById(id, updateDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("teacher/{id}")
    @Operation(summary = "Obtiene un profesor de acuerdo al Id ingresado",
    description = "Endpoint encargado de obtener los datos de un profesor.")
    public ResponseEntity<DocenteGetDto>getTeacherById(@PathVariable Long id){
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @GetMapping("/teachers")
    @Operation(summary = "Lista todos los profesores disponibles.",
    description = "Endpoint encargado de obtener todos los profesores.")
    public ResponseEntity<Set<DocenteGetDto>>getAlTeachers(){
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }




}
