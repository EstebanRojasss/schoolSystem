package com.schoolSystem.entities;

import com.schoolSystem.entities.rol.Rol;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Column(name = "contrasenha")
    private String contrasenha;
    @Column(name = "email")
    private String email;
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToOne(mappedBy = "idUsuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Estudiante estudiante;
    @OneToOne(mappedBy = "idUsuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Docente docente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles;

    public Usuario(Long id, String nombreUsuario, String contrasenha, String email, LocalDate fechaRegistro, Estado estado) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenha = contrasenha;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    public Usuario() {
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
