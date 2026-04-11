package com.ReWo.BibliotecaPublica_TT.repository;

import com.ReWo.BibliotecaPublica_TT.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long>
{
    Optional<Rol> findByNombreRol(String nombre);

    boolean existsByNombreRol(String nombre);
}
