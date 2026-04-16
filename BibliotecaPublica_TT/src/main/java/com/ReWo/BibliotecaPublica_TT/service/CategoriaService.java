package com.ReWo.BibliotecaPublica_TT.service;

import com.ReWo.BibliotecaPublica_TT.entity.Categoria;

import java.util.List;

public interface CategoriaService
{
    List<Categoria> listarTodas();

    Categoria buscarPorId(Long idCategoria);

    Categoria buscarPorNombre(String nombre);

    Categoria guardar(Categoria categoria);

    Categoria actualizar(Long idCategoria, Categoria categoriaActualizada);

    void eliminar(Long idCategoria);
}