package com.schoolSystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nota")
    private Short nota;

    @ManyToOne
    @JoinColumn(name = "idEstudiante", nullable = false)
    private Estudiante estudiante;
    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    private Curso curso;


    public Calificacion(Short nota, Estudiante estudiante, Curso curso) {
        this.nota = nota;
        this.estudiante = estudiante;
        this.curso = curso;
    }

    public Calificacion() {
    }

    public Short getNota() {
        return nota;
    }

    public void setNota(Short nota) {
        this.nota = nota;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }
}
