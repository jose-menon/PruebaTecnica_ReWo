package com.ReWo.BibliotecaPublica_TT.service;

import com.ReWo.BibliotecaPublica_TT.dto.ReporteCategoriaDTO;
import com.ReWo.BibliotecaPublica_TT.dto.ReporteEdadDTO;

import java.util.List;

public interface ReporteService
{
    List<ReporteCategoriaDTO> obtenerReporteUsoPorCategoria();

    List<ReporteEdadDTO> obtenerReporteUsoPorEdad();
}
