package com.ReWo.BibliotecaPublica_TT.repository;

import com.ReWo.BibliotecaPublica_TT.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long>
{
    Optional<Libro> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByAutorContainingIgnoreCase(String autor);

    List<Libro> findByCategoriaNombreCategoriaIgnoreCase(String nombreCategoria);

    List<Libro> findByEjemplaresDisponiblesGreaterThan(Integer cantidad);

    List<Libro> findByEjemplaresDisponiblesEquals(Integer cantidad);

    List<Libro> findByTituloContainingIgnoreCaseAndCategoriaNombreCategoriaIgnoreCase(String titulo, String categoria);

    @Query("""
        SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :texto, '%'))
        OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :texto, '%'))
        OR LOWER(l.isbn) LIKE LOWER(CONCAT('%', :texto, '%'))
""")
    List<Libro> buscarEnCatalogo(String texto);
    @Query("""
        SELECT l FROM Libro l WHERE l.ejemplaresDisponibles = 0
""")
    List<Libro> obtenerLibrosAgotados();
}
