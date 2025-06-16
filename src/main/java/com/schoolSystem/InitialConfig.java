package com.schoolSystem;

import com.schoolSystem.entities.Curso;
import com.schoolSystem.entities.rol.Rol;
import com.schoolSystem.entities.rol.TipoRol;
import com.schoolSystem.repository.CursoRepository;
import com.schoolSystem.repository.RolRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Random;

@Service
public class InitialConfig {


    private final RolRepository rolRepository;

    private final CursoRepository cursoRepository;

    private final Random creditosRandom;

    public InitialConfig(RolRepository rolRepository, CursoRepository cursoRepository, Random creditosRandom) {
        this.rolRepository = rolRepository;
        this.cursoRepository = cursoRepository;
        this.creditosRandom = creditosRandom;
    }


    @PostConstruct
    public void inicializarDatos() {
        cargarRolesBD();
        cargarCursosBD();
    }

    public void cargarRolesBD() {
        for (TipoRol tipoRol : TipoRol.values()) {
            Rol rol = new Rol();
            rol.setTipoRol(tipoRol);
            rolRepository.save(rol);
        }
    }

    public void cargarCursosBD() {
        for (String nombre : nombresCurso()) {
            Curso curso = new Curso();
            curso.setNombreCurso(nombre);
            curso.setCreditos(cantidadCreditosCurso());
            cursoRepository.save(curso);
        }
    }


    private String[] nombresCurso() {
        return new String[]{"Primero", "Segundo", "Tercero", "Cuarto", "Quinto"};
    }

    private short cantidadCreditosCurso() {
        return (short) creditosRandom.nextInt(50, 60);
    }


}
