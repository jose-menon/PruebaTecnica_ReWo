package com.ReWo.BibliotecaPublica_TT.service.Impl;

import com.ReWo.BibliotecaPublica_TT.entity.*;
import com.ReWo.BibliotecaPublica_TT.exception.BusinessException;
import com.ReWo.BibliotecaPublica_TT.exception.ResourceNotFoundException;
import com.ReWo.BibliotecaPublica_TT.repository.*;
import com.ReWo.BibliotecaPublica_TT.service.PrestamoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService
{
    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;
    private final MultaRepository multaRepository;
    private final ReservaRepository reservaRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, UsuarioRepository usuarioRepository, LibroRepository libroRepository, MultaRepository multaRepository, ReservaRepository reservaRepository)
    {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
        this.libroRepository = libroRepository;
        this.multaRepository = multaRepository;
        this.reservaRepository = reservaRepository;
    }
    @Override
    @Transactional
    public Prestamo registrarPrestamo(Long idUsuario, Long idLibro)
    {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado o inexistente"));

        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado o inexistente"));

        if(libro.getEjemplaresDisponibles() <= 0)
        {
            throw new BusinessException("No hay ejemplares disponibles");
        }
        boolean yaTienePrestamoActivo = prestamoRepository.existsByUsuarioPrestamoIdUsuarioAndLibroIdLibroAndEstado(idUsuario, idLibro, EstadoPrestamo.ACTIVO);
        if(yaTienePrestamoActivo)
        {
            throw new RuntimeException("El usuario ya tiene un prestamo activo del libro seleccionado");
        }
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() - 1);
        libroRepository.save(libro);

        Prestamo prestamo = Prestamo.builder()
                .usuarioPrestamo(usuario)
                .libro(libro)
                .fechaPrestamo(LocalDate.now())
                .fechaDevolucionPrevista(LocalDate.now().plusDays(7))
                .estado(EstadoPrestamo.ACTIVO)
                .build();
        return prestamoRepository.save(prestamo);
    }
    @Override
    @Transactional
    public Prestamo registrarDevolucion(Long idPrestamo)
    {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Prestamo inexistente"));

        if(prestamo.getEstado() == EstadoPrestamo.DEVUELTO)
        {
            throw new RuntimeException("El prestamo ya se a devuelto");
        }
        LocalDate fechaDevolucion = LocalDate.now();
        prestamo.setFechaDevolucionReal(fechaDevolucion);
        prestamo.setEstado(EstadoPrestamo.DEVUELTO);

        Libro libro = prestamo.getLibro();
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() + 1);
        libroRepository.save(libro);
        if(fechaDevolucion.isAfter(prestamo.getFechaDevolucionPrevista()))
        {
            long diasRetraso = ChronoUnit.DAYS.between(prestamo.getFechaDevolucionPrevista(),fechaDevolucion);
            double monto = diasRetraso * 10.0;

            Multa multa = Multa.builder()
                    .prestamo(prestamo)
                    .diasRetraso((int) diasRetraso)
                    .monto(monto)
                    .build();
            multaRepository.save(multa);
            prestamo.setMulta(multa);
        }
        prestamoRepository.save(prestamo);

        List<Reserva> reservasActivas = reservaRepository.findByLibro_IdLibroAndEstadoOrderByFechaReservaAsc(libro.getIdLibro(), EstadoReserva.ACTIVA);
        if(!reservasActivas.isEmpty())
        {
            Reserva primeraReserva = reservasActivas.get(0);
            primeraReserva.setEstado(EstadoReserva.ATENDIDA);
            reservaRepository.save(primeraReserva);
        }
        return prestamo;
    }
    @Override
    public Prestamo buscarPorId(Long idPrestamo)
    {
        return prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado con el id: " + idPrestamo));
    }
    @Override
    public List<Prestamo> obtenerHistorialUsuario(Long idUsuario)
    {
        return prestamoRepository.obtenerHistorialUsuario(idUsuario);
    }
    @Override
    public List<Prestamo> obtenerPrestamosVigentesUsuario(Long idUsuario)
    {
        return prestamoRepository.obtenerPrestamosVigentesUsuario(idUsuario, EstadoPrestamo.ACTIVO);
    }
    @Override
    public List<Prestamo> obtenerPrestamosAtrasados(){
        return prestamoRepository.obtenerPrestamosAtrasados(LocalDate.now());
    }
}
