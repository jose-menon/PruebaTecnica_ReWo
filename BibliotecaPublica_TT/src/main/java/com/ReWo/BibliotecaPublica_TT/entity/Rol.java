package com.ReWo.BibliotecaPublica_TT.entity;

import jakarta.persistence.*;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Rol
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private int id_rol;
    @Column(name = "nombreRol")
    private String nombreRol;
    @OneToMany(mappedBy = "rol")
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public Rol() {
    }

    public Rol(int id_rol, String nombreRol) {
        this.id_rol = id_rol;
        this.nombreRol = nombreRol;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
