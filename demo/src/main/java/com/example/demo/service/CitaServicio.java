package com.example.demo.service;

import com.example.demo.Dto.CitaDTO;
import com.example.demo.repository.CitaRepositorio;
import com.example.demo.repository.DiagnosticoDuenoRespositorio;
import com.example.demo.repository.MascotaRepositorio;
import com.example.demo.repository.VeterinarioRepositorio;
import com.example.demo.modelo.Cita;
import com.example.demo.modelo.DiagnosticoDueno;
import com.example.demo.modelo.Mascota;
import com.example.demo.modelo.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServicio {

    @Autowired
    private CitaRepositorio citaRepositorio;

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Autowired
    private VeterinarioRepositorio veterinarioRepositorio;

    @Autowired
    private DiagnosticoDuenoRespositorio diagnosticoDuenoRepositorio;

    public Cita crearCita(CitaDTO citaDTO) {
        Mascota mascota = mascotaRepositorio.findById(citaDTO.getIdMascota())
                .orElseThrow(() -> new IllegalArgumentException("Mascota no encontrada."));
        Veterinario veterinario = veterinarioRepositorio.findById(citaDTO.getIdVeterinario())
                .orElseThrow(() -> new IllegalArgumentException("Veterinario no encontrado."));
        DiagnosticoDueno diagnostico = diagnosticoDuenoRepositorio.findById(citaDTO.getIdDiagnostico())
                .orElseThrow(() -> new IllegalArgumentException("Diagnóstico no encontrado."));

        Cita nuevaCita = new Cita();
        nuevaCita.setFechaCita(citaDTO.getFechaCita());
        nuevaCita.setMotivoCita(citaDTO.getMotivoCita());
        nuevaCita.setEstadoCita(citaDTO.getEstadoCita());
        nuevaCita.setMascota(mascota);
        nuevaCita.setVeterinario(veterinario);
        nuevaCita.setDiagnostico(diagnostico);
        return citaRepositorio.save(nuevaCita);
    }

    public List<Cita> obtenerTodas() {
        return citaRepositorio.findAll();
    }

    public Optional<Cita> obtenerPorId(Integer id) {
        return citaRepositorio.findById(id);
    }

    public List<Cita> obtenerPorEstado(String estado) {
        return citaRepositorio.findByEstadoCita(estado);
    }

    public Cita actualizarCita(Integer id, CitaDTO citaDTO) {
        return citaRepositorio.findById(id).map(cita -> {
            cita.setFechaCita(citaDTO.getFechaCita());
            cita.setMotivoCita(citaDTO.getMotivoCita());
            cita.setEstadoCita(citaDTO.getEstadoCita());

            citaRepositorio.save(cita);
            return cita;
        }).orElse(null);
    }

    public void eliminarCita(Integer id) {
        citaRepositorio.deleteById(id);
    }
}