package com.example.demo.service;

import com.example.demo.Dto.DiagnosticoDuenoDTO;
import com.example.demo.repository.DiagnosticoDuenoRespositorio;
import com.example.demo.repository.MascotaRepositorio;
import com.example.demo.repository.VeterinarioRepositorio;
import com.example.demo.modelo.DiagnosticoDueno;
import com.example.demo.modelo.Mascota;
import com.example.demo.modelo.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoDuenoServicio {

    @Autowired
    private DiagnosticoDuenoRespositorio diagnosticoDuenoRepositorio;

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Autowired
    private VeterinarioRepositorio veterinarioRepositorio;

    public DiagnosticoDueno crearDiagnostico(DiagnosticoDuenoDTO diagnosticoDuenoDTO) {
        Mascota mascota = mascotaRepositorio.findById(diagnosticoDuenoDTO.getIdMascota())
                .orElseThrow(() -> new IllegalArgumentException("Mascota no encontrada."));
        Veterinario veterinario = veterinarioRepositorio.findById(diagnosticoDuenoDTO.getIdVeterinario())
                .orElseThrow(() -> new IllegalArgumentException("Veterinario no encontrado."));

        DiagnosticoDueno nuevoDiagnostico = new DiagnosticoDueno();
        nuevoDiagnostico.setFechaDiagnostico(diagnosticoDuenoDTO.getFechaDiagnostico());
        nuevoDiagnostico.setObservaciones(diagnosticoDuenoDTO.getObservaciones());
        nuevoDiagnostico.setMascota(mascota);
        nuevoDiagnostico.setVeterinario(veterinario);
        return diagnosticoDuenoRepositorio.save(nuevoDiagnostico);
    }

    public List<DiagnosticoDueno> obtenerTodos() {
        return diagnosticoDuenoRepositorio.findAll();
    }

    public Optional<DiagnosticoDueno> obtenerPorId(Integer id) {
        return diagnosticoDuenoRepositorio.findById(id);
    }

    public List<DiagnosticoDueno> buscarPorObservaciones(String observaciones) {
        return diagnosticoDuenoRepositorio.findByObservacionesContainingIgnoreCase(observaciones);
    }

    public DiagnosticoDueno actualizarDiagnostico(Integer id, DiagnosticoDuenoDTO diagnosticoDuenoDTO) {
        return diagnosticoDuenoRepositorio.findById(id).map(diagnostico -> {
            diagnostico.setFechaDiagnostico(diagnosticoDuenoDTO.getFechaDiagnostico());
            diagnostico.setObservaciones(diagnosticoDuenoDTO.getObservaciones());

            diagnosticoDuenoRepositorio.save(diagnostico);
            return diagnostico;
        }).orElse(null);
    }

    public void eliminarDiagnostico(Integer id) {
        diagnosticoDuenoRepositorio.deleteById(id);
    }
}
