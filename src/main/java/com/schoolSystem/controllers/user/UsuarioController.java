package com.schoolSystem.controllers.user;

import com.schoolSystem.dto.cursoDto.CursoGetDto;
import com.schoolSystem.service.user.DocenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final DocenteService docenteService;

    public UsuarioController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping("/teacher")
    public ResponseEntity<Set<CursoGetDto>> getTeacherCourses(@RequestParam Long id) {
        return ResponseEntity.ok(docenteService.getDocenteCursos(id));
    }

    @PostMapping("/teacher/calificacion")
    public ResponseEntity<String>setGrade(@RequestParam Long studentId, Short nota, String nombreCurso){
        docenteService.setCalificacionByStudentId(studentId, nota, nombreCurso);
        return ResponseEntity.ok("La calificación se asignó correctamente");
    }

}
