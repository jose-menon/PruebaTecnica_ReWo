package com.ReWo.BibliotecaPublica_TT.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "reservas")
@Builder
public class Reserva
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private long id_reserva;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario reservaUsuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_libro")
    private Libro libro;
    @Column(name = "fechaReserva")
    private LocalDate fechaReserva;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoReserva estado;

    public Reserva() {
    }

    public Reserva(long id_reserva, Usuario reservaUsuario, Libro libro, LocalDate fechaReserva, EstadoReserva estado)
    {
        this.id_reserva = id_reserva;
        this.reservaUsuario = reservaUsuario;
        this.libro = libro;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
    }

    public long getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(long id_reserva) {
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

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
}
