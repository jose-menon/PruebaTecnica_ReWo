package com.ReWo.BibliotecaPublica_TT.controller;

import com.ReWo.BibliotecaPublica_TT.entity.Prestamo;
import com.ReWo.BibliotecaPublica_TT.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoController
{
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }
    @PostMapping
    public ResponseEntity<Prestamo> registrarPrestamo(@RequestParam Long idUsuario, @RequestParam Long idLibro)
    {
        Prestamo prestamo = prestamoService.registrarPrestamo(idUsuario, idLibro);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamo);
    }
    @PutMapping("/devolucion/{idPrestamo}")
    public ResponseEntity<Prestamo> registrarDevolucion(@PathVariable Long idPrestamo)
    {
        return ResponseEntity.ok(prestamoService.registrarDevolucion(idPrestamo));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(prestamoService.buscarPorId(id));
    }
    @GetMapping("/usuario/{idUsuario}/historial")
    public ResponseEntity<List<Prestamo>> obtenerHistorialUsuario(@PathVariable Long idUsuario)
    {
        return ResponseEntity.ok(prestamoService.obtenerHistorialUsuario(idUsuario));
    }
    @GetMapping("/usuario/{idUsuario}/vigentes")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosVigentesUsuario(@PathVariable Long idUsuario)
    {
        return ResponseEntity.ok(prestamoService.obtenerPrestamosVigentesUsuario(idUsuario));
    }
    @GetMapping("/atrasados")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosAtrasados()
    {
        return ResponseEntity.ok(prestamoService.obtenerPrestamosAtrasados());
    }
}
