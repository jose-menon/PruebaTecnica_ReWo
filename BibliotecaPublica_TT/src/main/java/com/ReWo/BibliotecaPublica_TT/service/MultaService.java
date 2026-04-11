package com.ReWo.BibliotecaPublica_TT.service;

import com.ReWo.BibliotecaPublica_TT.entity.Multa;

import java.util.List;

public interface MultaService
{
    Multa buscarPorId(Long idMulta);

    Multa buscarPorPrestamo(Long idPrestamo);

    List<Multa> obtenerMultasPorUsuario(Long idUsuario);
}
