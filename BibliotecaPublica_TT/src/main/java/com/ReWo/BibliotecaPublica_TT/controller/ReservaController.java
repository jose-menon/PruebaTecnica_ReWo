package com.ReWo.BibliotecaPublica_TT.controller;

import com.ReWo.BibliotecaPublica_TT.entity.Reserva;
import com.ReWo.BibliotecaPublica_TT.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController
{
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
    @PostMapping
    public ResponseEntity<Reserva> registrarReserva(@RequestParam Long idUsuario, @RequestParam Long idLibro)
    {
        Reserva reserva = reservaService.registrarReserva(idUsuario, idLibro);
        return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
    }
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Reserva>> obtenerReservasPorUsuario(@PathVariable Long idUsuario)
    {
        return ResponseEntity.ok(reservaService.obtenerReservasPorUsuario(idUsuario));
    }
    @GetMapping("/libro/{idLibro}")
    public ResponseEntity<List<Reserva>> obtenerReservasActivasPorLibro(@PathVariable Long idLibro)
    {
        return ResponseEntity.ok(reservaService.obtenerReservasActivasPorLibro(idLibro));
    }
    @PutMapping("/cancelar/{idReserva}")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Long idReserva)
    {
        return ResponseEntity.ok(reservaService.cancelarReserva(idReserva));
    }
    @PutMapping("/atender/{idLibro}")
    public ResponseEntity<Reserva> atenderPrimeraReserva(@PathVariable Long idLibro)
    {
        Reserva reserva = reservaService.atenderPrimeraReserva(idLibro);
        if( reserva == null)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reserva);
    }
}
