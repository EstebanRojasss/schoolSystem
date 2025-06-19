//package com.schoolSystem;
//
//import com.schoolSystem.entities.Curso;
//import com.schoolSystem.entities.rol.Rol;
//import com.schoolSystem.entities.rol.TipoRol;
//import com.schoolSystem.repository.CursoRepository;
//import com.schoolSystem.repository.RolRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Service;
//
//import java.util.Random;
//
//@Service
//public class InitialConfig {
//
//
//    private final RolRepository rolRepository;
//
//    private final CursoRepository cursoRepository;
//
//    public InitialConfig(RolRepository rolRepository, CursoRepository cursoRepository) {
//        this.rolRepository = rolRepository;
//        this.cursoRepository = cursoRepository;
//    }
//
//
//    @PostConstruct
//    public void inicializarDatos() {
//        cargarRolesBD();
//        cargarCursosBD();
//    }
//
//    public void cargarRolesBD() {
//        for (TipoRol tipoRol : TipoRol.values()) {
//            Rol rol = new Rol();
//            rol.setTipoRol(tipoRol);
//            rolRepository.save(rol);
//        }
//    }
//
//    public void cargarCursosBD() {
//        for (String nombre : nombresCurso()) {
//            Curso curso = new Curso();
//            curso.setNombreCurso(nombre);
//            curso.setCreditos(cantidadCreditosCurso());
//            cursoRepository.save(curso);
//        }
//    }
//
//
//    private String[] nombresCurso() {
//        return new String[]{"Primero", "Segundo", "Tercero", "Cuarto", "Quinto"};
//    }
//
//    private short cantidadCreditosCurso() {
//        Random creditosRandom = new Random();
//        return (short) creditosRandom.nextInt(50, 60);
//    }
//
//
//}
