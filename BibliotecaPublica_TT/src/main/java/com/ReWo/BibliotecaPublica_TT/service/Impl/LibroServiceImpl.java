package com.ReWo.BibliotecaPublica_TT.service.Impl;

import com.ReWo.BibliotecaPublica_TT.entity.Libro;
import com.ReWo.BibliotecaPublica_TT.repository.LibroRepository;
import com.ReWo.BibliotecaPublica_TT.service.LibroService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService
{
    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository)
    {
        this.libroRepository = libroRepository;
    }
    @Override
    public List<Libro> listarTodos()
    {
        return libroRepository.findAll();
    }
    @Override
    public Libro buscarPorId(Long idLibro)
    {
        return libroRepository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Id del libro no encontrado: " + idLibro));
    }
    @Override
    public Libro buscarPorIsbn(String isbn)
    {
        return libroRepository.findByIsbn(isbn)
                .orElseThrow(()-> new RuntimeException("No encontramos el libro con el codigo IBSN: " + isbn));
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo)
    {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }
    @Override
    public List<Libro> buscarPorAutor(String autor)
    {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }
    @Override
    public List<Libro> buscarPorCategoria(String categoria)
    {
        return libroRepository.findByCategoriaNombreCategoriaIgnoreCase(categoria);
    }
    @Override
    public List<Libro> buscarEnCatalogo(String texto)
    {
        return libroRepository.buscarEnCatalogo(texto);
    }
    @Override
    public List<Libro> obtenerLibrosDisponibles()
    {
        return libroRepository.findByEjemplaresDisponiblesGreaterThan(0);
    }
    @Override
    public List<Libro> obtenerLibrosAgotados()
    {
        return libroRepository.obtenerLibrosAgotados();
    }
    @Override
    public Libro guardar(Libro libro)
    {
        if(libroRepository.existsByIsbn((libro.getIsbn())))
        {
            throw new RuntimeException("Libro ya registrado con ese ISBN");
        }
        return libroRepository.save(libro);
    }
    @Override
    public Libro actualizar(Long idLibro, Libro libroActualizado)
    {
        Libro libro = buscarPorId(idLibro);

        libro.setTitulo(libroActualizado.getTitulo());
        libro.setAutor(libroActualizado.getAutor());
        libro.setIsbn(libroActualizado.getIsbn());
        libro.setCategoria(libroActualizado.getCategoria());
        libro.setEjemplaresTotales(libroActualizado.getEjemplaresTotales());
        libro.setEjemplaresDisponibles(libroActualizado.getEjemplaresDisponibles());

        return libroRepository.save(libro);
    }
    @Override
    public void eliminar(Long idLibro)
    {
        Libro libro = buscarPorId(idLibro);
        libroRepository.delete(libro);
    }
}
