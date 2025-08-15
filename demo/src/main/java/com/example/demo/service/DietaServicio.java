package com.example.demo.service;

import com.example.demo.Dto.DietaDTO;
import com.example.demo.repository.DietaRepositorio;
import com.example.demo.repository.MascotaRepositorio;
import com.example.demo.repository.VeterinarioRepositorio;
import com.example.demo.modelo.Dieta;
import com.example.demo.modelo.Mascota;
import com.example.demo.modelo.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietaServicio {

    @Autowired
    private DietaRepositorio dietaRepositorio;

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Autowired
    private VeterinarioRepositorio veterinarioRepositorio;

    public Dieta crearDieta(DietaDTO dietaDTO) {
        Mascota mascota = mascotaRepositorio.findById(dietaDTO.getIdMascota())
                .orElseThrow(() -> new IllegalArgumentException("Mascota no encontrada."));
        Veterinario veterinario = veterinarioRepositorio.findById(dietaDTO.getIdVeterinario())
                .orElseThrow(() -> new IllegalArgumentException("Veterinario no encontrado."));

        Dieta nuevaDieta = new Dieta();
        nuevaDieta.setDescripcion(dietaDTO.getDescripcion());
        nuevaDieta.setTipoDieta(dietaDTO.getTipoDieta());
        nuevaDieta.setMascota(mascota);
        nuevaDieta.setVeterinario(veterinario);
        return dietaRepositorio.save(nuevaDieta);
    }

    public List<Dieta> obtenerTodas() {
        return dietaRepositorio.findAll();
    }

    public Optional<Dieta> obtenerPorId(Integer id) {
        return dietaRepositorio.findById(id);
    }

    public List<Dieta> obtenerPorTipoDieta(String tipoDieta) {
        return dietaRepositorio.findByTipoDieta(tipoDieta);
    }

    public Dieta actualizarDieta(Integer id, DietaDTO dietaDTO) {
        return dietaRepositorio.findById(id).map(dieta -> {
            dieta.setDescripcion(dietaDTO.getDescripcion());
            dieta.setTipoDieta(dietaDTO.getTipoDieta());

            dietaRepositorio.save(dieta);
            return dieta;
        }).orElse(null);
    }

    public void eliminarDieta(Integer id) {
        dietaRepositorio.deleteById(id);
    }
}