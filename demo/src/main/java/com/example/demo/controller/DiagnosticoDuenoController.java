package com.example.demo.controller;


import com.example.demo.Dto.DiagnosticoDuenoDTO;
import com.example.demo.modelo.DiagnosticoDueno;
import com.example.demo.service.DiagnosticoDuenoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosticos-duenos")
public class DiagnosticoDuenoController {

    @Autowired
    private DiagnosticoDuenoServicio diagnosticoDuenoServicio;

    @PostMapping
    public ResponseEntity<DiagnosticoDueno> crearDiagnostico(@RequestBody DiagnosticoDuenoDTO diagnosticoDuenoDTO) {
        try {
            DiagnosticoDueno nuevoDiagnostico = diagnosticoDuenoServicio.crearDiagnostico(diagnosticoDuenoDTO);
            return new ResponseEntity<>(nuevoDiagnostico, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticoDueno>> obtenerTodosLosDiagnosticos() {
        List<DiagnosticoDueno> diagnosticos = diagnosticoDuenoServicio.obtenerTodos();
        return new ResponseEntity<>(diagnosticos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticoDueno> obtenerDiagnosticoPorId(@PathVariable("id") Integer id) {
        return diagnosticoDuenoServicio.obtenerPorId(id)
                .map(diagnostico -> new ResponseEntity<>(diagnostico, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/observaciones/{observaciones}")
    public ResponseEntity<List<DiagnosticoDueno>> buscarPorObservaciones(@PathVariable("observaciones") String observaciones) {
        List<DiagnosticoDueno> diagnosticos = diagnosticoDuenoServicio.buscarPorObservaciones(observaciones);
        if (!diagnosticos.isEmpty()) {
            return new ResponseEntity<>(diagnosticos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticoDueno> actualizarDiagnostico(@PathVariable("id") Integer id, @RequestBody DiagnosticoDuenoDTO diagnosticoDuenoDTO) {
        DiagnosticoDueno diagnosticoActualizado = diagnosticoDuenoServicio.actualizarDiagnostico(id, diagnosticoDuenoDTO);
        if (diagnosticoActualizado != null) {
            return new ResponseEntity<>(diagnosticoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarDiagnostico(@PathVariable("id") Integer id) {
        diagnosticoDuenoServicio.eliminarDiagnostico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}