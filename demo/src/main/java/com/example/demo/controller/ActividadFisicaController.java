package com.example.demo.controller;


import com.example.demo.Dto.ActividadFisicaDTO;
import com.example.demo.modelo.ActividadFisica;
import com.example.demo.service.ActividadFisicaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades-fisicas")
public class ActividadFisicaController {

    @Autowired
    private ActividadFisicaServicio actividadFisicaServicio;

    @PostMapping
    public ResponseEntity<ActividadFisica> crearActividadFisica(@RequestBody ActividadFisicaDTO actividadFisicaDTO) {
        try {
            ActividadFisica nuevaActividad = actividadFisicaServicio.crearActividadFisica(actividadFisicaDTO);
            return new ResponseEntity<>(nuevaActividad, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ActividadFisica>> obtenerTodasLasActividades() {
        List<ActividadFisica> actividades = actividadFisicaServicio.obtenerTodas();
        return new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActividadFisica> obtenerActividadPorId(@PathVariable("id") Integer id) {
        return actividadFisicaServicio.obtenerPorId(id)
                .map(actividad -> new ResponseEntity<>(actividad, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ActividadFisica>> obtenerActividadesPorTipo(@PathVariable("tipo") String tipo) {
        List<ActividadFisica> actividades = actividadFisicaServicio.obtenerPorTipo(tipo);
        if (!actividades.isEmpty()) {
            return new ResponseEntity<>(actividades, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActividadFisica> actualizarActividadFisica(@PathVariable("id") Integer id, @RequestBody ActividadFisicaDTO actividadFisicaDTO) {
        ActividadFisica actividadActualizada = actividadFisicaServicio.actualizarActividadFisica(id, actividadFisicaDTO);
        if (actividadActualizada != null) {
            return new ResponseEntity<>(actividadActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarActividadFisica(@PathVariable("id") Integer id) {
        actividadFisicaServicio.eliminarActividadFisica(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}