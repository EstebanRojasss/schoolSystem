package com.schoolSystem.service.admin;

import com.schoolSystem.dto.docenteDto.DocenteCreateDto;
import com.schoolSystem.dto.docenteDto.DocenteGetDto;
import com.schoolSystem.dto.docenteDto.DocenteUpdateDto;
import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.Docente;
import com.schoolSystem.entities.Estado;
import com.schoolSystem.entities.Usuario;
import com.schoolSystem.exception.CursoNotFoundException;
import com.schoolSystem.exception.DocenteNotFoundException;
import com.schoolSystem.exception.UserNotFoundException;
import com.schoolSystem.mapper.docente.DocenteToDocenteGetDto;
import com.schoolSystem.mapper.docente.DocenteUpdateDtoToDocente;
import com.schoolSystem.repository.CursoRepository;
import com.schoolSystem.repository.DocenteRepository;
import com.schoolSystem.repository.UsuarioRepository;
import com.schoolSystem.service.ComprobarRolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminDocenteService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DocenteRepository docenteRepository;
    private final DocenteToDocenteGetDto docenteGetMapper;
    private final DocenteUpdateDtoToDocente docenteUpdateMapper;

    public AdminDocenteService(CursoRepository cursoRepository,
                               UsuarioRepository usuarioRepository,
                               DocenteRepository docenteRepository,
                               DocenteToDocenteGetDto docenteGetMapper,
                               DocenteUpdateDtoToDocente docenteUpdateMapper) {
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
        this.docenteRepository = docenteRepository;
        this.docenteGetMapper = docenteGetMapper;
        this.docenteUpdateMapper = docenteUpdateMapper;
    }

    @Transactional
    public void createTeacher(DocenteCreateDto dto){
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

        ComprobarRolService.comprAsignacionRolDocente(usuario);

        docente.setUsuario(usuario);
        docente.setEstado(Estado.ACTIVO);
        docenteRepository.save(docente);
    }

    public Set<DocenteGetDto> getAllTeachers(){
        return docenteRepository
                .findAll()
                .stream()
                .map(docenteGetMapper::map)
                .collect(Collectors.toSet());
    }

    public DocenteGetDto getTeacherById(Long id){
        return docenteRepository
                .findById(id).map(docenteGetMapper::map)
                .orElseThrow(() -> new DocenteNotFoundException("El docente no existe. Compruebe los datos."));
    }

    private Docente getEntityTeacher(Long id){
        return docenteRepository
                .findById(id)
                .orElseThrow(() -> new DocenteNotFoundException("El docente no existe. Compruebe los datos"));
    }

    public void updateTeacherById(Long id, DocenteUpdateDto updateDto){
        Curso curso = cursoRepository
                .findByNombreCurso(updateDto.curso())
                .orElseThrow(() -> new CursoNotFoundException("El curso no existe. Compruebe los datos."));

        docenteUpdateMapper.update(getEntityTeacher(id), updateDto, curso);

        docenteRepository.save(getEntityTeacher(id));
    }

}
