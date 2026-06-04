package com.ReWo.BibliotecaPublica_TT.service.Impl;

import com.ReWo.BibliotecaPublica_TT.dto.MultaDTO;
import com.ReWo.BibliotecaPublica_TT.entity.Multa;
import com.ReWo.BibliotecaPublica_TT.entity.Prestamo;
import com.ReWo.BibliotecaPublica_TT.repository.MultaRepository;
import com.ReWo.BibliotecaPublica_TT.repository.PrestamoRepository;
import com.ReWo.BibliotecaPublica_TT.service.MultaMapper;
import com.ReWo.BibliotecaPublica_TT.service.MultaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MultaServiceImpl implements MultaService
{
    private final MultaRepository multaRepository;
    private final PrestamoRepository prestamoRepository;

    public MultaServiceImpl(MultaRepository multaRepository, PrestamoRepository prestamoRepository) {
        this.multaRepository = multaRepository;
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public MultaDTO buscarPorId(Long idMulta)
    {
        Multa multa = multaRepository.findById(idMulta)
                .orElseThrow(() -> new RuntimeException("El id: " + idMulta + " no fue encontrado"));
        return MultaMapper.toDto(multa);
    }
    @Override
    public MultaDTO buscarPorPrestamo(Long idPrestamo)
    {
        generarMultasPorPrestamosAtrasados();
        Multa multa = multaRepository.findByPrestamoIdPrestamo(idPrestamo)
                .orElseThrow(() -> new RuntimeException("El id: " + idPrestamo + " no registra multas"));
        return MultaMapper.toDto(multa);
    }
    @Override
    public List<MultaDTO> obtenerMultasPorUsuario(Long idUsuario)
    {
        generarMultasPorPrestamosAtrasados();
        return multaRepository.findByPrestamoUsuarioPrestamoIdUsuario(idUsuario)
                .stream()
                .map(MultaMapper::toDto)
                .toList();
    }
    @Override
    public List<MultaDTO> listarTodas()
    {
        generarMultasPorPrestamosAtrasados();
        return multaRepository.findAll()
                .stream()
                .map(MultaMapper::toDto)
                .toList();


    }
    @Transactional
    @Override
    public void generarMultasPorPrestamosAtrasados()
    {
        List<Prestamo> prestamos = prestamoRepository.findAll();

        LocalDate hoy = LocalDate.now();

        for(Prestamo prestamo : prestamos)
        {
            if(prestamo.getFechaDevolucionReal() == null
                    && prestamo.getFechaDevolucionPrevista() != null
                    && prestamo.getFechaDevolucionPrevista().isBefore(hoy))
            {

                long diasAtraso = ChronoUnit.DAYS.between(prestamo.getFechaDevolucionPrevista(),hoy);

                double monto = diasAtraso * 10.0;
                Multa multa = multaRepository.findByPrestamo(prestamo)
                                .orElse(new Multa());
                multa.setPrestamo(prestamo);
                multa.setDiasRetraso((int) diasAtraso);
                multa.setMonto(monto);

                multaRepository.save(multa);
            }
        }
    }
}
