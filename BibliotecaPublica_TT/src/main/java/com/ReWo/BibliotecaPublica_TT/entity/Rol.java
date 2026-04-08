package com.ReWo.BibliotecaPublica_TT.entity;

public class Rol
{
    private int id_rol;
    private String nombreRol;

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
