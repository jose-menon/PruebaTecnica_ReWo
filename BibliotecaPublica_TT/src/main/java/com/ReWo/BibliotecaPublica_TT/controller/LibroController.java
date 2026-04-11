package com.ReWo.BibliotecaPublica_TT.controller;

import com.ReWo.BibliotecaPublica_TT.entity.Libro;
import com.ReWo.BibliotecaPublica_TT.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class LibroController
{
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> listarTodos()
    {
        return ResponseEntity.ok(libroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(libroService.buscarPorId(id));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Libro> buscarPorIsbn(@PathVariable String isbn)
    {
        return ResponseEntity.ok(libroService.buscarPorIsbn(isbn));
    }
    @GetMapping("/titulo")
    public ResponseEntity<List<Libro>> buscarPorTitulo(@PathVariable String texto)
    {
        return ResponseEntity.ok(libroService.buscarPorTitulo(texto));
    }
    @GetMapping("/autor")
    public ResponseEntity<List<Libro>> buscarPorAutor(@PathVariable String texto)
    {
        return ResponseEntity.ok(libroService.buscarPorAutor(texto));
    }
    @GetMapping("/categoria")
    public ResponseEntity<List<Libro>> buscarPorCategoria(@PathVariable String nombre)
    {
        return ResponseEntity.ok(libroService.buscarPorCategoria(nombre));
    }
    @GetMapping("/catalogo")
    public ResponseEntity<List<Libro>> buscarEnCatalogo(@PathVariable String texto)
    {
        return ResponseEntity.ok(libroService.buscarEnCatalogo(texto));
    }
    @GetMapping("/disponibles")
    public ResponseEntity<List<Libro>> obtenerDisponibles()
    {
        return ResponseEntity.ok(libroService.obtenerLibrosDisponibles());
    }
    @GetMapping("/agotados")
    public ResponseEntity<List<Libro>> obtenerAgotados()
    {
        return ResponseEntity.ok(libroService.obtenerLibrosAgotados());
    }
    @PostMapping
    public ResponseEntity<Libro> guardar(@RequestBody Libro libro)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(libroService.guardar(libro));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro libroActualizado)
    {
        return ResponseEntity.ok(libroService.actualizar(id, libroActualizado));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id)
    {
        libroService.eliminar(id);
        return ResponseEntity.ok("Libro eliminado correctamente");
    }
}
