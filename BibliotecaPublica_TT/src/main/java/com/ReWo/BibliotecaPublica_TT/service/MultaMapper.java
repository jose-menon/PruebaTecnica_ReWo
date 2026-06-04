package com.ReWo.BibliotecaPublica_TT.service;

import com.ReWo.BibliotecaPublica_TT.dto.MultaDTO;
import com.ReWo.BibliotecaPublica_TT.entity.Multa;

public class MultaMapper
{
    public static MultaDTO toDto(Multa multa)
    {
        MultaDTO dto = new MultaDTO();

        dto.setIdMulta(multa.getIdMulta());
        dto.setIdPrestamo(multa.getPrestamo().getIdPrestamo());
        dto.setDiasRetraso(multa.getDiasRetraso());
        dto.setMonto(multa.getMonto());

        if(multa.getPrestamo() != null)
        {
            dto.setIdPrestamo(
                    multa.getPrestamo().getIdPrestamo()
            );

            if(multa.getPrestamo().getUsuarioPrestamo() != null)
            {
                dto.setIdUsuario(
                        multa.getPrestamo()
                                .getUsuarioPrestamo()
                                .getIdUsuario()
                );
                dto.setNombreUsuario(
                        multa.getPrestamo()
                                .getUsuarioPrestamo()
                                .getNombreUsuario()
                );

                dto.setApellidoUsuario(
                        multa.getPrestamo()
                                .getUsuarioPrestamo()
                                .getApellidoUsuario()
                );
            }
            if(multa.getPrestamo().getLibro() != null)
            {
                dto.setIdLibro(
                        multa.getPrestamo()
                                .getLibro()
                                .getIdLibro()
                );
                dto.setTitulo(
                        multa.getPrestamo()
                                .getLibro()
                                .getTitulo()
                );
            }
        }
        return dto;
    }
}
