package com.ReWo.BibliotecaPublica_TT.service;

import com.ReWo.BibliotecaPublica_TT.entity.Reserva;

import java.util.List;

public interface ReservaService
{
    Reserva registrarReserva(Long idUsuario, Long idLibro);

    List<Reserva> obtenerReservasPorUsuario(Long idUsuario);

    List<Reserva> obtenerReservasActivasPorLibro(Long idLibro);

    Reserva cancelarReserva(Long idReserva);
    
    Reserva atenderPrimeraReserva(Long idLibro);
}
