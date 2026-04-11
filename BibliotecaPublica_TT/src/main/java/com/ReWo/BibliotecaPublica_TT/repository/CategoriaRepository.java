package com.ReWo.BibliotecaPublica_TT.repository;

import com.ReWo.BibliotecaPublica_TT.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>
{
    Optional<Categoria> findByNombreCategoria(String nombre);

    boolean existsByNombreCategoria(String nombre);
}
