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
    private Long idMulta;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prestamo")
    private Prestamo prestamo;
    @Column(name = "diasRetraso")
    private Integer diasRetraso;
    @Column(name = "monto")
    private Double monto;

    public Multa() {
    }

    public Multa(Long idMulta, Prestamo prestamo, Integer diasRetraso, Double monto) {
        this.idMulta = idMulta;
        this.prestamo = prestamo;
        this.diasRetraso = diasRetraso;
        this.monto = monto;
    }

    public Long getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(Long idMulta) {
        this.idMulta = idMulta;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Integer getDiasRetraso() {
        return diasRetraso;
    }

    public void setDiasRetraso(Integer diasRetraso) {
        this.diasRetraso = diasRetraso;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
