package com.ReWo.BibliotecaPublica_TT.dto;

public class ReporteCategoriaDTO
{
    private String categoria;
    private Long totalPrestamos;

    public ReporteCategoriaDTO(String categoria, Long totalPrestamos) {
        this.categoria = categoria;
        this.totalPrestamos = totalPrestamos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getTotalPrestamos() {
        return totalPrestamos;
    }

    public void setTotalPrestamos(Long totalPrestamos) {
        this.totalPrestamos = totalPrestamos;
    }
}
