package com.ReWo.BibliotecaPublica_TT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Rol
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private long id_rol;
    @Column(name = "nombreRol")
    private String nombreRol;
    @JsonIgnore
    @OneToMany(mappedBy = "rolUsuario")
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public Rol() {
    }

    public Rol(long id_rol, String nombreRol, List<Usuario> listaUsuarios) {
        this.id_rol = id_rol;
        this.nombreRol = nombreRol;
        this.listaUsuarios = listaUsuarios;
    }

    public long getId_rol() {
        return id_rol;
    }

    public void setId_rol(long id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
