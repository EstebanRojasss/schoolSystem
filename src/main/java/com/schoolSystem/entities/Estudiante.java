package com.schoolSystem.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Estudiante extends Persona {
    @Enumerated(EnumType.STRING)
    private Estado estado;

    private LocalDate fechaInscripcion;

    @OneToOne
    @JoinColumn(name = "usuario", unique = true, nullable = false)
    private Usuario idUsuario;

    public Estudiante(Long id, String nombre, String apellido, String numeroDocumento, LocalDate fechaNacimiento, String direccion, String telefono, String email, Estado estado, LocalDate fechaInscripcion, Usuario idUsuario) {
        super(id, nombre, apellido, numeroDocumento, fechaNacimiento, direccion, telefono, email);
        this.estado = estado;
        this.fechaInscripcion = fechaInscripcion;
        this.idUsuario = idUsuario;
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
