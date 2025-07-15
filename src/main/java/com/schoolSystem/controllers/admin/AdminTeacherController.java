package com.schoolSystem.controllers.admin;

import com.schoolSystem.dto.docenteDto.DocenteCreateDto;
import com.schoolSystem.dto.docenteDto.DocenteGetDto;
import com.schoolSystem.dto.docenteDto.DocenteUpdateDto;
import com.schoolSystem.service.admin.AdminDocenteService;
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
    public ResponseEntity<String>createTeacher(@RequestBody DocenteCreateDto createDto){
        teacherService.createTeacher(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("teacher/{id}")
    public ResponseEntity<String>updateTeacher(@RequestBody DocenteUpdateDto updateDto, @PathVariable Long id){
        teacherService.updateTeacherById(id, updateDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("teacher/{id}")
    public ResponseEntity<DocenteGetDto>getTeacherById(@PathVariable Long id){
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @GetMapping("/teachers")
    public ResponseEntity<Set<DocenteGetDto>>getAlTeachers(){
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }




}
