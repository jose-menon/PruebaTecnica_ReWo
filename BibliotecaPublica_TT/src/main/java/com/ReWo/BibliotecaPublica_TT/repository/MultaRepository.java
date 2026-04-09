package com.ReWo.BibliotecaPublica_TT.repository;

import com.ReWo.BibliotecaPublica_TT.entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MultaRepository extends JpaRepository<Multa, Long>
{
    Optional<Multa> findByPrestamoIdPrestamo(Long idPrestamo);

    List<Multa> findByPrestamoUsuarioIdUsuario(Long idUsuario);

    List<Multa> findByMontoGreaterThan(Double monto);
}
