package com.ReWo.BibliotecaPublica_TT.repository;

import com.ReWo.BibliotecaPublica_TT.dto.ReporteCategoriaDTO;
import com.ReWo.BibliotecaPublica_TT.dto.ReporteEdadDTO;
import com.ReWo.BibliotecaPublica_TT.entity.EstadoPrestamo;
import com.ReWo.BibliotecaPublica_TT.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>
{
    List<Prestamo> findByUsuarioIdUsuario(Long idUsuario);

    List<Prestamo> findByLibroIdLibro(Long idLibro);

    List<Prestamo> findByEstado(EstadoPrestamo estado);

    List<Prestamo> findByUsuarioIdUsuarioAndEstado(Long idUsuario, EstadoPrestamo estado);

    List<Prestamo> findByFechaPrestamo(LocalDate fechaPrestamo);

    List<Prestamo> findByFechaPrestamoBetween(LocalDate desde, LocalDate hasta);

    List<Prestamo> findByFechaDevolucionPrevistaBeforeAndFechaDevolucionRealIsNull(LocalDate fecha);

    boolean existsByUsuarioIdUsuarioAndLibroIdLibroAndEstado(Long idUsuario, Long idLibro, EstadoPrestamo estado);

    @Query("""
        SELECT p FROM Prestamo p WHERE p.usuario.idUsuario = :idUsuario
            ORDER BY p.fechaPrestamo DESC
    """)
    List<Prestamo> obtenerHistorialUsuario(Long idUsuario);

    @Query("""
        SELECT p FROM Prestamo p WHERE p.usuario.idUsuario = :idUsuario
        AND p.estado = com.biblioteca.digital.enums.EstadoPrestamos.ACTIVO
        ORDER BY p.fechaPrestamo DESC
""")
    List<Prestamo> obtenerPrestamosVigentesUsuario(Long idUsuario);

    @Query("""
        SELECT p FROM Prestamo p WHERE p.fechaDevolucionReal IS NULL
            AND p.fechaDevolucionPrevista < :fechaActual
    """)
    List<Prestamo> obtenerPrestamosAtrasados(LocalDate fechaActual);

    @Query("""
        SELECT new com.biblioteca.digital.dto.ReporteCategoriaDTO(
                p.libro.categoria.nombre,
                COUNT(p)
                )
        FROM Prestamo p
        GROUP BY p.libro.categoria.nombre
        ORDER BY COUNT(p) DESC
        """)
    List<ReporteCategoriaDTO> reporteUsoPorCategoria();

    @Query("""
        SELECT new com.biblioteca.digital.dto.ReporteEdadDTO(
                p.usuario.edad,
                COUNT(p)
                )
        FROM Prestamo p
            GROUP BY p.usuario.edad
            ORDER BY p.usuario.edad ASC
        """)
    List<ReporteEdadDTO> reporteUsoPorEdad();
}
