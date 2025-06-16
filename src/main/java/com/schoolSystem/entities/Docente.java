package com.schoolSystem.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "docente")
public class Docente extends Persona {

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToOne
    @JoinColumn(name = "idUsuario", unique = true, nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "docente_curso",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursos;

    public Docente(Long id, String nombre, String apellido, String numeroDocumento, LocalDate fechaNacimiento, String direccion, String telefono, String email, Estado estado, Usuario usuario, Set<Curso> cursos) {
        super(id, nombre, apellido, numeroDocumento, fechaNacimiento, direccion, telefono, email);
        this.estado = estado;
        this.usuario = usuario;
        this.cursos = cursos;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public Docente() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


}
