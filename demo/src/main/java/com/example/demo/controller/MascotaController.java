package com.example.demo.controller;

import com.example.demo.Dto.MascotaDTO;
import com.example.demo.modelo.Mascota;
import com.example.demo.service.MascotaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaServicio mascotaServicio;

    @PostMapping
    public ResponseEntity<Mascota> crearMascota(@RequestBody MascotaDTO mascotaDTO) {
        try {
            Mascota nuevaMascota = mascotaServicio.crearMascota(mascotaDTO);
            return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Mascota>> obtenerTodasLasMascotas() {
        List<Mascota> mascotas = mascotaServicio.obtenerTodos();
        return new ResponseEntity<>(mascotas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable("id") Integer id) {
        return mascotaServicio.obtenerPorId(id)
                .map(mascota -> new ResponseEntity<>(mascota, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable("id") Integer id, @RequestBody MascotaDTO mascotaDTO) {
        Mascota mascotaActualizada = mascotaServicio.actualizarMascota(id, mascotaDTO);
        if (mascotaActualizada != null) {
            return new ResponseEntity<>(mascotaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarMascota(@PathVariable("id") Integer id) {
        mascotaServicio.eliminarMascota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
