package com.schoolSystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "nombre_curso")
    private String nombreCurso;
    @Column(name = "creditos")
    private Short creditos;

    public Curso(Long id, String nombreCurso, Short creditos) {
        this.id = id;
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
    }

    public Curso() {
    }

    public Long getId() {
        return id;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Short getCreditos() {
        return creditos;
    }

    public void setCreditos(Short creditos) {
        this.creditos = creditos;
    }
}
