package com.schoolSystem.entities.rol;

public class Rol {
    private final Long id;
    private TipoRol tipoRol;


    public Rol(Long id, TipoRol tipoRol) {
        this.id = id;
        this.tipoRol = tipoRol;
    }

    public Long getId() {
        return id;
    }

    public TipoRol getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(TipoRol tipoRol) {
        this.tipoRol = tipoRol;
    }


}
