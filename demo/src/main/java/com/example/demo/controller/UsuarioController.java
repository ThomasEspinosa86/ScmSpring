package com.example.demo.controller;


import com.example.demo.Dto.UsuarioDTO;
import com.example.demo.modelo.Usuario;
import com.example.demo.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario nuevoUsuario = usuarioServicio.crearUsuario(usuarioDTO);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioServicio.obtenerTodos();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable("id") Integer id) {
        return usuarioServicio.obtenerPorId(id)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("id") Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioActualizado = usuarioServicio.actualizarUsuario(id, usuarioDTO);
        if (usuarioActualizado != null) {
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioServicio.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}