package com.schoolSystem.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estudiante")
public class Estudiante extends Persona {
    @Enumerated(EnumType.STRING)
    private Estado estado;

    private LocalDate fechaInscripcion;

    @OneToOne
    @JoinColumn(name = "idUsuario", unique = true, nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "estudiante_curso",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursos = new HashSet<>();

    public Estudiante(Long id, String nombre, String apellido, String numeroDocumento, LocalDate fechaNacimiento, String direccion, String telefono, String email, Estado estado, LocalDate fechaInscripcion, Usuario usuario, Set<Curso> cursos) {
        super(id, nombre, apellido, numeroDocumento, fechaNacimiento, direccion, telefono, email);
        this.estado = estado;
        this.fechaInscripcion = fechaInscripcion;
        this.usuario = usuario;
        this.cursos = cursos;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    private void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }


    public Estudiante() {

    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void addCurso(Curso curso){
        this.cursos.add(curso);
    }
}
