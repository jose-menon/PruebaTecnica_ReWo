package com.ReWo.BibliotecaPublica_TT.dto;

public class ReporteEdadDTO
{
    private Integer edad;
    private Long totalPrestamos;

    public ReporteEdadDTO(Integer edad, Long totalPrestamos) {
        this.edad = edad;
        this.totalPrestamos = totalPrestamos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Long getTotalPrestamos() {
        return totalPrestamos;
    }

    public void setTotalPrestamos(Long totalPrestamos) {
        this.totalPrestamos = totalPrestamos;
    }
}
