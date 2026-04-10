package com.ReWo.BibliotecaPublica_TT.service.Impl;

import com.ReWo.BibliotecaPublica_TT.entity.EstadoReserva;
import com.ReWo.BibliotecaPublica_TT.entity.Libro;
import com.ReWo.BibliotecaPublica_TT.entity.Reserva;
import com.ReWo.BibliotecaPublica_TT.entity.Usuario;
import com.ReWo.BibliotecaPublica_TT.repository.LibroRepository;
import com.ReWo.BibliotecaPublica_TT.repository.ReservaRepository;
import com.ReWo.BibliotecaPublica_TT.repository.UsuarioRepository;
import com.ReWo.BibliotecaPublica_TT.service.ReservaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService
{
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository, LibroRepository libroRepository)
    {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.libroRepository = libroRepository;
    }
    @Override
    @Transactional
    public Reserva registrarReserva(Long idUsuario, Long idLibro)
    {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario Inexistente"));
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        if(libro.getEjemplaresDisponibles() > 0)
        {
            throw new RuntimeException("El libro posee ejemplares disponibles, no corresponde reservar");
        }
        boolean yaReservado = reservaRepository.existsByReservaUsuarioIdUsuarioAndLibroIdLibroAndEstado(idUsuario, idLibro, EstadoReserva.ACTIVA);
        if(yaReservado)
        {
            throw new RuntimeException("El usuario ya posee una reserva activa del libro");
        }
        Reserva reserva = Reserva.builder()
                .reservaUsuario(usuario)
                .libro(libro)
                .fechaReserva(LocalDate.now())
                .estado(EstadoReserva.ACTIVA)
                .build();

        return reservaRepository.save(reserva);
    }
    @Override
    public List<Reserva> obtenerReservasPorUsuario(Long idUsuario)
    {
        return reservaRepository.obtenerHistorialReservasUsuarios(idUsuario);
    }
    @Override
    public List<Reserva> obtenerReservasActivasPorLibro(Long idLibro)
    {
        return reservaRepository.obtenerReservasActivasPorLibro(idLibro, EstadoReserva.ACTIVA);
    }
    @Override
    @Transactional
    public Reserva cancelarReserva(Long idReserva)
    {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("La reserva no fue encontrada"));
        reserva.setEstado(EstadoReserva.CANCELADA);
        return reservaRepository.save(reserva);
    }
    @Override
    @Transactional
    public Reserva atenderPrimeraReserva(Long idLibro)
    {
        List<Reserva> reservas = reservaRepository.obtenerReservasActivasPorLibro(idLibro, EstadoReserva.ACTIVA);
        if(reservas.isEmpty())
        {
            return null;
        }
        Reserva primeraReserva = reservas.get(0);
        primeraReserva.setEstado(EstadoReserva.ATENDIDA);
        return reservaRepository.save(primeraReserva);
    }

}
