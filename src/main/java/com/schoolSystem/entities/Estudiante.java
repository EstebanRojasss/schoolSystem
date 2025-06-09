package com.schoolSystem.entities;

import java.time.LocalDate;

public class Estudiante extends Persona{
    private Estado estado;
    private LocalDate fechaInscripcion;

    private Usuario usuario;

    public Estudiante(Long id, String nombre, String apellido, String numeroDocumento, LocalDate fechaNacimiento, String direccion, String telefono, String email, Estado estado, LocalDate fechaInscripcion, Usuario usuario) {
        super(id, nombre, apellido, numeroDocumento, fechaNacimiento, direccion, telefono, email);
        this.estado = estado;
        this.fechaInscripcion = fechaInscripcion;
        this.usuario = usuario;
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
}
