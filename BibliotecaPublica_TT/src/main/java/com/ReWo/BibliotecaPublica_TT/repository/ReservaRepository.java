package com.ReWo.BibliotecaPublica_TT.repository;

import com.ReWo.BibliotecaPublica_TT.entity.EstadoReserva;
import com.ReWo.BibliotecaPublica_TT.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>
{
    List<Reserva> findByReservaUsuarioIdUsuario(Long idUsuario);

    List<Reserva> findByLibroIdLibro(Long idLibro);

    List<Reserva> findByEstado(EstadoReserva estado);

    List<Reserva> findByReservaUsuarioIdUsuarioAndEstado(Long idUsuario, EstadoReserva estado);

    boolean existsByReservaUsuarioIdUsuarioAndLibroIdLibroAndEstado(Long idUsuario, Long idLibro, EstadoReserva estado);

    @Query("""
        SELECT r FROM Reserva r WHERE r.libro.idLibro = :idLibro
        AND r.estado = :estado
        ORDER BY r.fechaReserva ASC
""")
    List<Reserva> obtenerReservasActivasPorLibro(Long idLibro, EstadoReserva estado);

    @Query("""
        SELECT r FROM Reserva r WHERE r.reservaUsuario.idUsuario = :idUsuario 
            ORDER BY r.fechaReserva DESC
    """)
    List<Reserva> obtenerHistorialReservasUsuarios(Long idUsuario);
}
