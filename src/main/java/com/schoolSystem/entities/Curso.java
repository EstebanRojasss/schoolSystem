package com.schoolSystem.entities;

public class Curso {
    private final Long id;
    private String nombreCurso;
    private Short creditos;

    public Curso(Long id, String nombreCurso, Short creditos) {
        this.id = id;
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
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
