package com.ReWo.BibliotecaPublica_TT.entity;

import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.List;

public class Reserva
{
    private int id_reserva;
    @ManyToOne
    private Usuario reservaUsuario;
    @ManyToOne
    private Libro libro;
    private Date fechaReserva;
    private String estado;

    public Reserva() {
    }

    public Reserva(int id_reserva, Usuario reservaUsuario, Libro libro, Date fechaReserva, String estado)
    {
        this.id_reserva = id_reserva;
        this.reservaUsuario = reservaUsuario;
        this.libro = libro;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Usuario getReservaUsuario() {
        return reservaUsuario;
    }

    public void setReservaUsuario(Usuario reservaUsuario) {
        this.reservaUsuario = reservaUsuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
