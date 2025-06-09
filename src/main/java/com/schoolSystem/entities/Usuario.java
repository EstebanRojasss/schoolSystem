package com.schoolSystem.entities;

import com.schoolSystem.entities.rol.Rol;

import java.time.LocalDate;
import java.util.Set;

public class Usuario {
    private final Long id;
    private String nombreUsuario;
    private String contrasenha;
    private String email;
    private LocalDate fechaRegistro;
    private Estado estado;

    private Set<Rol> roles;

    public Usuario(Long id, String nombreUsuario, String contrasenha, String email, LocalDate fechaRegistro, Estado estado) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenha = contrasenha;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }


    public Long getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public void addRole(Rol rol){
        this.roles.add(rol);
    }



}
