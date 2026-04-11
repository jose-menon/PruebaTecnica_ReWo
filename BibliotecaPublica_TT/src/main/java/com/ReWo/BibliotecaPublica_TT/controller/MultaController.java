package com.ReWo.BibliotecaPublica_TT.controller;

import com.ReWo.BibliotecaPublica_TT.entity.Multa;
import com.ReWo.BibliotecaPublica_TT.service.MultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
@CrossOrigin(origins = "*")
public class MultaController
{
    private final MultaService multaService;

    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Multa> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(multaService.buscarPorId(id));
    }
    @GetMapping("/prestamo/{idPrestamo}")
    public ResponseEntity<Multa> buscarPorPrestamo(@PathVariable Long idPrestamo)
    {
        return ResponseEntity.ok(multaService.buscarPorPrestamo(idPrestamo));
    }
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Multa>> obtenerMultasPorUsuario(@PathVariable Long idUsuario)
    {
        return ResponseEntity.ok(multaService.obtenerMultasPorUsuario(idUsuario));
    }
}
