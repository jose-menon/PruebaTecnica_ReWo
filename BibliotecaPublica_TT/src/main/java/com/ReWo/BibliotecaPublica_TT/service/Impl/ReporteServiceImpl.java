package com.ReWo.BibliotecaPublica_TT.service.Impl;

import com.ReWo.BibliotecaPublica_TT.dto.ReporteCategoriaDTO;
import com.ReWo.BibliotecaPublica_TT.dto.ReporteEdadDTO;
import com.ReWo.BibliotecaPublica_TT.repository.PrestamoRepository;
import com.ReWo.BibliotecaPublica_TT.service.ReporteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService
{
    private final PrestamoRepository prestamoRepository;

    public ReporteServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }
    @Override
    public List<ReporteCategoriaDTO> obtenerReporteUsoPorCategoria()
    {
        return prestamoRepository.reporteUsoPorCategoria();
    }
    @Override
    public List<ReporteEdadDTO> obtenerReporteUsoPorEdad()
    {
        return prestamoRepository.reporteUsoPorEdad();
    }
}
