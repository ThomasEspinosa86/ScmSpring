package com.example.demo.controller;

import com.example.demo.modelo.Rol;
import com.example.demo.service.RolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolServicio rolServicio;

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        Rol nuevoRol = rolServicio.crearRol(rol);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Rol>> obtenerTodosLosRoles() {
        List<Rol> roles = rolServicio.obtenerTodos();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable("id") Integer id) {
        return rolServicio.obtenerPorId(id)
                .map(rol -> new ResponseEntity<>(rol, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarRol(@PathVariable("id") Integer id) {
        rolServicio.eliminarRol(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
