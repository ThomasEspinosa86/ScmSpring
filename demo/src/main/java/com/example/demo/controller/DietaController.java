package com.example.demo.controller;


import com.example.demo.Dto.DietaDTO;
import com.example.demo.modelo.Dieta;
import com.example.demo.service.DietaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dietas")
public class DietaController {

    @Autowired
    private DietaServicio dietaServicio;

    @PostMapping
    public ResponseEntity<Dieta> crearDieta(@RequestBody DietaDTO dietaDTO) {
        try {
            Dieta nuevaDieta = dietaServicio.crearDieta(dietaDTO);
            return new ResponseEntity<>(nuevaDieta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Dieta>> obtenerTodasLasDietas() {
        List<Dieta> dietas = dietaServicio.obtenerTodas();
        return new ResponseEntity<>(dietas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dieta> obtenerDietaPorId(@PathVariable("id") Integer id) {
        return dietaServicio.obtenerPorId(id)
                .map(dieta -> new ResponseEntity<>(dieta, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Dieta>> obtenerDietasPorTipo(@PathVariable("tipo") String tipo) {
        List<Dieta> dietas = dietaServicio.obtenerPorTipoDieta(tipo);
        if (!dietas.isEmpty()) {
            return new ResponseEntity<>(dietas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dieta> actualizarDieta(@PathVariable("id") Integer id, @RequestBody DietaDTO dietaDTO) {
        Dieta dietaActualizada = dietaServicio.actualizarDieta(id, dietaDTO);
        if (dietaActualizada != null) {
            return new ResponseEntity<>(dietaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarDieta(@PathVariable("id") Integer id) {
        dietaServicio.eliminarDieta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}