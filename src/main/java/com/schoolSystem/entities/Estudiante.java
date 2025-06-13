package com.schoolSystem.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "estudiante")
public class Estudiante extends Persona {
    @Enumerated(EnumType.STRING)
    private Estado estado;

    private LocalDate fechaInscripcion;

    @OneToOne
    @JoinColumn(name = "idUsuario", unique = true, nullable = false)
    private Usuario idUsuario;

    @ManyToMany
    @JoinTable(
            name = "estudiante_curso",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursos;

    public Estudiante(Long id, String nombre, String apellido, String numeroDocumento, LocalDate fechaNacimiento, String direccion, String telefono, String email, Estado estado, LocalDate fechaInscripcion, Usuario idUsuario, Set<Curso> cursos) {
        super(id, nombre, apellido, numeroDocumento, fechaNacimiento, direccion, telefono, email);
        this.estado = estado;
        this.fechaInscripcion = fechaInscripcion;
        this.idUsuario = idUsuario;
        this.cursos = cursos;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
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

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
