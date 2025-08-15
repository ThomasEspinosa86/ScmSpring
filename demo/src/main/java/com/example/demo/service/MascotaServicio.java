package com.example.demo.service;

import com.example.demo.Dto.MascotaDTO;
import com.example.demo.repository.MascotaRepositorio;
import com.example.demo.repository.UsuarioRepositorio;
import com.example.demo.modelo.Mascota;
import com.example.demo.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaServicio {

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Mascota crearMascota(MascotaDTO mascotaDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(mascotaDTO.getIdUsuario());
        if (usuarioOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con el ID: " + mascotaDTO.getIdUsuario());
        }

        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setNombre(mascotaDTO.getNombre());
        nuevaMascota.setGenero(mascotaDTO.getGenero());
        nuevaMascota.setFechaNacimiento(mascotaDTO.getFechaNacimiento());
        nuevaMascota.setRaza(mascotaDTO.getRaza());
        nuevaMascota.setUsuario(usuarioOptional.get());
        return mascotaRepositorio.save(nuevaMascota);
    }

    public List<Mascota> obtenerTodos() {
        return mascotaRepositorio.findAll();
    }

    public Optional<Mascota> obtenerPorId(Integer id) {
        return mascotaRepositorio.findById(id);
    }

    public Mascota actualizarMascota(Integer id, MascotaDTO mascotaDTO) {
        return mascotaRepositorio.findById(id).map(mascota -> {
            mascota.setNombre(mascotaDTO.getNombre());
            mascota.setGenero(mascotaDTO.getGenero());
            mascota.setFechaNacimiento(mascotaDTO.getFechaNacimiento());
            mascota.setRaza(mascotaDTO.getRaza());

            Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(mascotaDTO.getIdUsuario());
            usuarioOptional.ifPresent(mascota::setUsuario);

            return mascotaRepositorio.save(mascota);
        }).orElse(null);
    }

    public void eliminarMascota(Integer id) {
        mascotaRepositorio.deleteById(id);
    }
}