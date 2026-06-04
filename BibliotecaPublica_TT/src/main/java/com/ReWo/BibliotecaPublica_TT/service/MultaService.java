package com.ReWo.BibliotecaPublica_TT.service;

import com.ReWo.BibliotecaPublica_TT.dto.MultaDTO;
import com.ReWo.BibliotecaPublica_TT.entity.Multa;

import java.util.List;

public interface MultaService
{
    MultaDTO buscarPorId(Long idMulta);

    MultaDTO buscarPorPrestamo(Long idPrestamo);

    List<MultaDTO> obtenerMultasPorUsuario(Long idUsuario);

    List<MultaDTO> listarTodas();

    void generarMultasPorPrestamosAtrasados();
}
