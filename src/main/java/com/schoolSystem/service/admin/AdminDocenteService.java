package com.schoolSystem.service.admin;

import com.schoolSystem.dto.docenteDto.DocenteCreateDto;
import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.Docente;
import com.schoolSystem.entities.Estado;
import com.schoolSystem.entities.Usuario;
import com.schoolSystem.exception.CursoNotFoundException;
import com.schoolSystem.exception.UserNotFoundException;
import com.schoolSystem.repository.CursoRepository;
import com.schoolSystem.repository.DocenteRepository;
import com.schoolSystem.repository.UsuarioRepository;
import com.schoolSystem.service.ComprobarRolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminDocenteService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DocenteRepository docenteRepository;

    public AdminDocenteService(CursoRepository cursoRepository, UsuarioRepository usuarioRepository, DocenteRepository docenteRepository) {
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
        this.docenteRepository = docenteRepository;
    }

    @Transactional
    public void crearDocente(DocenteCreateDto dto){
        Docente docente = new Docente();
        docente.setNombre(dto.nombre());
        docente.setApellido(dto.apellido());
        docente.setNumeroDocumento(dto.numeroDocumento());
        docente.setFechaNacimiento(dto.fecha_nacimiento());
        docente.setTelefono(dto.telefono());
        docente.setDireccion(dto.direccion());
        docente.setEmail(dto.email());

        for(Curso curso : dto.cursos()){
            Curso comprobarCurso = cursoRepository.findById(curso.getId())
                    .orElseThrow(() -> new CursoNotFoundException("El curso ingresado no coincide con ninguno disponible. Compruebe los datos.") );
            docente.addCurso(comprobarCurso);
        }
        Usuario usuario = usuarioRepository.findById(dto.id())
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe. Verifique la información."));

        ComprobarRolService.comprAsignacionRolEstudiante(usuario);

        docente.setUsuario(usuario);
        docente.setEstado(Estado.ACTIVO);
        docenteRepository.save(docente);
    }

}
