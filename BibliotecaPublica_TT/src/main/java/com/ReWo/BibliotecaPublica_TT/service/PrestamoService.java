package com.ReWo.BibliotecaPublica_TT.service;

import com.ReWo.BibliotecaPublica_TT.entity.Prestamo;

import java.util.List;

public interface PrestamoService
{
    Prestamo registrarPrestamo(Long idUsuario, Long idLibro);

    Prestamo registrarDevolucion(Long idPrestamo);

    Prestamo buscarPorId(Long idPrestamo);

    List<Prestamo> obtenerHistorialUsuario(Long idUsuario);

    List<Prestamo> obtenerPrestamosVigentesUsuario(Long idUsuario);

    List<Prestamo> obtenerPrestamosAtrasados();
}
