package com.ReWo.BibliotecaPublica_TT.service;

import com.ReWo.BibliotecaPublica_TT.entity.Libro;

import java.util.List;

public interface LibroService
{
    List<Libro> listarTodos();

    Libro buscarPorId(Long idLibro);

    Libro buscarPorIsbn(String isbn);

    List<Libro> buscarPorTitulo(String titulo);

    List<Libro> buscarPorAutor(String autor);

    List<Libro> buscarPorCategoria(String categoria);

    List<Libro> buscarEnCatalogo(String texto);

    List<Libro> obtenerLibrosDisponibles();

    List<Libro> obtenerLibrosAgotados();

    Libro guardar(Libro libro);

    Libro actualizar(Long idLibro, Libro libroActualizado);

    void eliminar(Long idLibro);
}
