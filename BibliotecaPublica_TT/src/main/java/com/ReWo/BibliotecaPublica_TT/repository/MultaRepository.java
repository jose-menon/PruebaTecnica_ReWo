package com.ReWo.BibliotecaPublica_TT.repository;

import com.ReWo.BibliotecaPublica_TT.entity.Multa;
import com.ReWo.BibliotecaPublica_TT.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MultaRepository extends JpaRepository<Multa, Long>
{
    Optional<Multa> findByPrestamoIdPrestamo(Long idPrestamo);

    Optional<Multa> findByPrestamo(Prestamo prestamo);

    List<Multa> findByPrestamoUsuarioPrestamoIdUsuario(Long idUsuario);

    List<Multa> findByDiasRetrasoGreaterThan(Integer dias);

    List<Multa> findByMontoGreaterThan(Double monto);

    boolean existsByPrestamo(Prestamo prestamo);
}
