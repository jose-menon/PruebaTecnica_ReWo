package com.ReWo.BibliotecaPublica_TT.entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String email;
    private String password;
    private int edad;
    private Rol rolUsuario;
    @OneToMany
    private List<Prestamo> listaPrestamo;
    @OneToMany
    private List<Reserva> listaReservas;

    public Usuario() {
    }

    public Usuario(int id_usuario, String nombreUsuario, String apellidoUsuario, String email, String password, int edad, Rol rolUsuario, List<Prestamo> listaPrestamo, List<Reserva> listaReservas)
    {
        this.id_usuario = id_usuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.email = email;
        this.password = password;
        this.edad = edad;
        this.rolUsuario = rolUsuario;
        this.listaPrestamo = listaPrestamo;
        this.listaReservas = listaReservas;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Rol getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(Rol rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public List<Prestamo> getListaPrestamo() {
        return listaPrestamo;
    }

    public void setListaPrestamo(List<Prestamo> listaPrestamo) {
        this.listaPrestamo = listaPrestamo;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }
}
