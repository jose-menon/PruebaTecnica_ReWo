package com.ReWo.BibliotecaPublica_TT.repository;

import com.ReWo.BibliotecaPublica_TT.entity.EstadoReserva;
import com.ReWo.BibliotecaPublica_TT.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>
{
    List<Reserva> findByUsuarioIdUsuario(Long idUsuario);

    List<Reserva> findByLibroIdLibro(Long idLibro);

    List<Reserva> findByEstado(EstadoReserva estado);

    List<Reserva> findByUsuarioIdUsuarioAndEstado(Long idUsuario, EstadoReserva estado);

    boolean existsByUsuarioIdUsuarioAndLibroIdLibroAndEstado(Long idUsuario, Long idLibro, EstadoReserva estado);

    @Query("""
        SELECT r FROM Reserva r WHERE r.libro.idLibro = :idLibro
        AND r.estado = com.biblioteca.digital.enums.EstadoReserva.ACTIVA
        ORDER BY r.fechaReserva ASC
""")
    List<Reserva> obtenerReservasActivasPorLibro(Long idLibro);

    @Query("""
        SELECT r FROM Reserva r WHERE r.usuario.idUsuario = :idUsuario 
            ORDER BY r.fechaReserva DESC
    """)
    List<Reserva> obtenerHistorialReservasUsuarios(Long idUsuario);
}
