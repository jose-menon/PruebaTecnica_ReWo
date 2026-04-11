package com.ReWo.BibliotecaPublica_TT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "usuarios")
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Column(name = "apellidoUsuario")
    private String apellidoUsuario;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "edad")
    private Integer edad;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private Rol rolUsuario;
    @JsonIgnore
    @OneToMany(mappedBy = "usuarioPrestamo")
    private List<Prestamo> listaPrestamo = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "reservaUsuario")
    private List<Reserva> listaReservas = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nombreUsuario, String apellidoUsuario, String email, String password, Integer edad, Rol rolUsuario, List<Prestamo> listaPrestamo, List<Reserva> listaReservas)
    {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.email = email;
        this.password = password;
        this.edad = edad;
        this.rolUsuario = rolUsuario;
        this.listaPrestamo = listaPrestamo;
        this.listaReservas = listaReservas;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
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
