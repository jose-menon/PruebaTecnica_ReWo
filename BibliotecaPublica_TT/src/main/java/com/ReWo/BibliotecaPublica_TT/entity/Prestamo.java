package com.ReWo.BibliotecaPublica_TT.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "prestamos")
@Builder
public class Prestamo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private long id_prestamo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioPrestamo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_libro")
    private Libro libro;
    @OneToOne(mappedBy = "prestamo")
    private Multa multa;
    @Column(name = "fechaPrestamo")
    private LocalDate fechaPrestamo;
    @Column(name = "fechaDevolucionPrevista")
    private LocalDate fechaDevolucionPrevista;
    @Column(name = "fechaDevolucionReal")
    private LocalDate fechaDevolucionReal;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPrestamo estado;

    public Prestamo() {
    }

    public Prestamo(long id_prestamo, Usuario usuarioPrestamo, Libro libro, Multa multa, LocalDate fechaPrestamo, LocalDate fechaDevolucionPrevista, LocalDate fechaDevolucionReal, EstadoPrestamo estado)
    {
        this.id_prestamo = id_prestamo;
        this.usuarioPrestamo = usuarioPrestamo;
        this.libro = libro;
        this.multa = multa;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estado = estado;
    }

    public long getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(long id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public Usuario getUsuarioPrestamo() {
        return usuarioPrestamo;
    }

    public void setUsuarioPrestamo(Usuario usuarioPrestamo) {
        this.usuarioPrestamo = usuarioPrestamo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }

    public void setFechaDevolucionPrevista(LocalDate fechaDevolucionPrevista) {
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }
}
