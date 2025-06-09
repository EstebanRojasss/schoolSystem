package com.schoolSystem.entities;

import java.time.LocalDate;

public class Docente extends Persona{
    private Estado estado;

    public Docente(Long id, String nombre, String apellido, String numeroDocumento, LocalDate fechaNacimiento, String direccion, String telefono, String email, Estado estado) {
        super(id, nombre, apellido, numeroDocumento, fechaNacimiento, direccion, telefono, email);
        this.estado = estado;
    }
    public Docente() {

    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


}
