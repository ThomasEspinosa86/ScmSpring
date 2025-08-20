package com.example.demo.controller;


import com.example.demo.Dto.CitaDTO;
import com.example.demo.modelo.Cita;
import com.example.demo.service.CitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaServicio citaServicio;

    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody CitaDTO citaDTO) {
        try {
            Cita nuevaCita = citaServicio.crearCita(citaDTO);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cita>> obtenerTodasLasCitas() {
        List<Cita> citas = citaServicio.obtenerTodas();
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerCitaPorId(@PathVariable("id") Integer id) {
        return citaServicio.obtenerPorId(id)
                .map(cita -> new ResponseEntity<>(cita, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Cita>> obtenerCitasPorEstado(@PathVariable("estado") String estado) {
        List<Cita> citas = citaServicio.obtenerPorEstado(estado);
        if (!citas.isEmpty()) {
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(@PathVariable("id") Integer id, @RequestBody CitaDTO citaDTO) {
        Cita citaActualizada = citaServicio.actualizarCita(id, citaDTO);
        if (citaActualizada != null) {
            return new ResponseEntity<>(citaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarCita(@PathVariable("id") Integer id) {
        citaServicio.eliminarCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
