package com.schoolSystem.controllers.admin;

import com.schoolSystem.dto.estudianteDto.EstudianteCreateDto;
import com.schoolSystem.dto.estudianteDto.EstudianteGetDto;
import com.schoolSystem.dto.estudianteDto.EstudianteUpdateDto;
import com.schoolSystem.service.admin.AdminEstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminStudentController {
    private final AdminEstudianteService estudianteService;

    public AdminStudentController(AdminEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("/student")
    @Operation(description = "Endpoint encargado de crear nuevo estudiante")
    public ResponseEntity<String> createStudent(@RequestBody EstudianteCreateDto estudianteCreateDto){
        estudianteService.createStudent(estudianteCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Estudiante creado con éxito.");
    }

    @GetMapping("/students")
    @Operation(description = "Endpoint encargado de listar todos los estudiantes")
    public ResponseEntity<Set<EstudianteGetDto>>getAllStudents(){
        return ResponseEntity.ok(estudianteService.getAllStudents());
    }

    @PutMapping("/student/{id}")
    @Operation(description = "Endpoint encargado de actualizar estudiante por Id")
    public ResponseEntity<String>updateStudentById(@PathVariable Long id, @RequestBody EstudianteUpdateDto updateDto){
        estudianteService.updateStudentById(id, updateDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Estudiante actualizado con éxito");
    }


    
}
