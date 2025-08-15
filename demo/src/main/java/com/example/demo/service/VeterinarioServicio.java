package com.example.demo.service;

import com.example.demo.Dto.VeterinarioDTO;
import com.example.demo.repository.UsuarioRepositorio;
import com.example.demo.repository.VeterinarioRepositorio;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioServicio {

    @Autowired
    private VeterinarioRepositorio veterinarioRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Veterinario crearVeterinario(VeterinarioDTO veterinarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(veterinarioDTO.getIdUsuario());
        if (usuarioOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con el ID: " + veterinarioDTO.getIdUsuario());
        }

        Veterinario nuevoVeterinario = new Veterinario();
        nuevoVeterinario.setEspecialidad(veterinarioDTO.getEspecialidad());
        nuevoVeterinario.setUsuario(usuarioOptional.get());
        return veterinarioRepositorio.save(nuevoVeterinario);
    }

    public List<Veterinario> obtenerTodos() {
        return veterinarioRepositorio.findAll();
    }

    public Optional<Veterinario> obtenerPorId(Integer id) {
        return veterinarioRepositorio.findById(id);
    }

    public List<Veterinario> obtenerPorEspecialidad(String especialidad) {
        return veterinarioRepositorio.findByEspecialidad(especialidad);
    }

    public Veterinario actualizarVeterinario(Integer id, VeterinarioDTO veterinarioDTO) {
        return veterinarioRepositorio.findById(id).map(veterinario -> {
            veterinario.setEspecialidad(veterinarioDTO.getEspecialidad());

            Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(veterinarioDTO.getIdUsuario());
            usuarioOptional.ifPresent(veterinario::setUsuario);

            return veterinarioRepositorio.save(veterinario);
        }).orElse(null);
    }

    public void eliminarVeterinario(Integer id) {
        veterinarioRepositorio.deleteById(id);
    }
}