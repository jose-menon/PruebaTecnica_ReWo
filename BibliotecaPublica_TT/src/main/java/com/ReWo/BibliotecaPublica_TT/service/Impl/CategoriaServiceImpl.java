package com.ReWo.BibliotecaPublica_TT.service.Impl;

import com.ReWo.BibliotecaPublica_TT.entity.Categoria;
import com.ReWo.BibliotecaPublica_TT.exception.BusinessException;
import com.ReWo.BibliotecaPublica_TT.exception.ResourceNotFoundException;
import com.ReWo.BibliotecaPublica_TT.repository.CategoriaRepository;
import com.ReWo.BibliotecaPublica_TT.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService
{
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(Long idCategoria) {
        return categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con id: " + idCategoria
                ));
    }

    @Override
    public Categoria buscarPorNombre(String nombre) {
        return categoriaRepository.findByNombreCategoria(nombre)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con nombre: " + nombre
                ));
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        if (categoriaRepository.existsByNombreCategoria(categoria.getNombreCategoria())) {
            throw new BusinessException("Ya existe una categoría con ese nombre");
        }

        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizar(Long idCategoria, Categoria categoriaActualizada) {
        Categoria categoria = buscarPorId(idCategoria);

        if (!categoria.getNombreCategoria().equalsIgnoreCase(categoriaActualizada.getNombreCategoria())
                && categoriaRepository.existsByNombreCategoria(categoriaActualizada.getNombreCategoria())) {
            throw new BusinessException("Ya existe otra categoría con ese nombre");
        }

        categoria.setNombreCategoria(categoriaActualizada.getNombreCategoria());

        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminar(Long idCategoria) {
        Categoria categoria = buscarPorId(idCategoria);
        categoriaRepository.delete(categoria);
    }
}