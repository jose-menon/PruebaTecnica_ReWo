package com.ReWo.BibliotecaPublica_TT.controller;

import com.ReWo.BibliotecaPublica_TT.entity.Categoria;
import com.ReWo.BibliotecaPublica_TT.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController
{
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @GetMapping("/nombre")
    public ResponseEntity<Categoria> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(categoriaService.buscarPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.guardar(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id,
                                                @RequestBody Categoria categoriaActualizada) {
        return ResponseEntity.ok(categoriaService.actualizar(id, categoriaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.ok("Categoría eliminada correctamente");
    }
}