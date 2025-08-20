package com.example.demo.controller;


import com.example.demo.Dto.VeterinarioDTO;
import com.example.demo.modelo.Veterinario;
import com.example.demo.service.VeterinarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioServicio veterinarioServicio;

    @PostMapping
    public ResponseEntity<Veterinario> crearVeterinario(@RequestBody VeterinarioDTO veterinarioDTO) {
        try {
            Veterinario nuevoVeterinario = veterinarioServicio.crearVeterinario(veterinarioDTO);
            return new ResponseEntity<>(nuevoVeterinario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Veterinario>> obtenerTodosLosVeterinarios() {
        List<Veterinario> veterinarios = veterinarioServicio.obtenerTodos();
        return new ResponseEntity<>(veterinarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> obtenerVeterinarioPorId(@PathVariable("id") Integer id) {
        return veterinarioServicio.obtenerPorId(id)
                .map(veterinario -> new ResponseEntity<>(veterinario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<Veterinario>> obtenerVeterinariosPorEspecialidad(@PathVariable("especialidad") String especialidad) {
        List<Veterinario> veterinarios = veterinarioServicio.obtenerPorEspecialidad(especialidad);
        if (!veterinarios.isEmpty()) {
            return new ResponseEntity<>(veterinarios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinario> actualizarVeterinario(@PathVariable("id") Integer id, @RequestBody VeterinarioDTO veterinarioDTO) {
        Veterinario veterinarioActualizado = veterinarioServicio.actualizarVeterinario(id, veterinarioDTO);
        if (veterinarioActualizado != null) {
            return new ResponseEntity<>(veterinarioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarVeterinario(@PathVariable("id") Integer id) {
        veterinarioServicio.eliminarVeterinario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}