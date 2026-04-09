package com.ReWo.BibliotecaPublica_TT.service.Impl;

import com.ReWo.BibliotecaPublica_TT.entity.Multa;
import com.ReWo.BibliotecaPublica_TT.repository.MultaRepository;
import com.ReWo.BibliotecaPublica_TT.service.MultaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultaServiceImpl implements MultaService
{
    private final MultaRepository multaRepository;

    public MultaServiceImpl(MultaRepository multaRepository) {
        this.multaRepository = multaRepository;
    }

    @Override
    public Multa buscarPorId(Long idMulta)
    {
        return multaRepository.findById(idMulta)
                .orElseThrow(() -> new RuntimeException("El id: " + idMulta + " no fue encontrado"));
    }
    @Override
    public Multa buscarPorPrestamo(Long idPrestamo)
    {
        return multaRepository.findByPrestamoIdPrestamo(idPrestamo)
                .orElseThrow(() -> new RuntimeException("El id: " + idPrestamo + " no registra multas"));
    }
    @Override
    public List<Multa> obtenerMultasPorUsuario(Long idUsuario)
    {
        return multaRepository.findByPrestamoUsuarioIdUsuario(idUsuario);
    }
}
