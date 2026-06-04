package com.ReWo.BibliotecaPublica_TT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MultaDTO
{
    private Long idMulta;
    private Integer diasRetraso;
    private Double monto;
    private Long idPrestamo;
    private Long idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private Long idLibro;
    private String titulo;

    public MultaDTO(){}

}
