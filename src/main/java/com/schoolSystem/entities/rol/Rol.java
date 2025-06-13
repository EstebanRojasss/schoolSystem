package com.schoolSystem.entities.rol;

import jakarta.persistence.*;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Enumerated(EnumType.STRING)
    private TipoRol tipoRol;

    public Rol(Long id, TipoRol tipoRol) {
        this.id = id;
        this.tipoRol = tipoRol;
    }

    public Rol() {
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
