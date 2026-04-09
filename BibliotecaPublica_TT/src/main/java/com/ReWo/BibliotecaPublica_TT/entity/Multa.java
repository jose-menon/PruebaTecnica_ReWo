package com.ReWo.BibliotecaPublica_TT.entity;

import jakarta.persistence.*;
import lombok.Builder;


@Entity
@Table(name = "multas")
@Builder
public class Multa
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multa")
    private long id_multa;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prestamo")
    private Prestamo prestamo;
    @Column(name = "diasRetraso")
    private int diasRetraso;
    @Column(name = "monto")
    private double monto;

    public Multa() {
    }

    public Multa(long id_multa, Prestamo prestamo, int diasRetraso, double monto)
    {
        this.id_multa = id_multa;
        this.prestamo = prestamo;
        this.diasRetraso = diasRetraso;
        this.monto = monto;
    }

    public long getId_multa() {
        return id_multa;
    }

    public void setId_multa(long id_multa) {
        this.id_multa = id_multa;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public int getDiasRetraso() {
        return diasRetraso;
    }

    public void setDiasRetraso(int diasRetraso) {
        this.diasRetraso = diasRetraso;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
