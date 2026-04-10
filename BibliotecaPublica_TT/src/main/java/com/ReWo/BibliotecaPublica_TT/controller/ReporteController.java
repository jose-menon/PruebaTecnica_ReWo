package com.ReWo.BibliotecaPublica_TT.controller;

import com.ReWo.BibliotecaPublica_TT.dto.ReporteCategoriaDTO;
import com.ReWo.BibliotecaPublica_TT.dto.ReporteEdadDTO;
import com.ReWo.BibliotecaPublica_TT.service.ReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController
{
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<ReporteCategoriaDTO>> obtenerReportePorCategoria()
    {
        return ResponseEntity.ok(reporteService.obtenerReporteUsoPorCategoria());
    }
    @GetMapping("/edad")
    public ResponseEntity<List<ReporteEdadDTO>> obtenerReportePorEdad()
    {
        return ResponseEntity.ok(reporteService.obtenerReporteUsoPorEdad());
    }
}
