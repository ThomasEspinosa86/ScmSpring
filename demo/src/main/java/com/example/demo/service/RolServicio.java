package com.example.demo.service;
import com.example.demo.repository.RolRepositorio;
import com.example.demo.modelo.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServicio {

    @Autowired
    private RolRepositorio rolRepositorio;

    public Rol crearRol(Rol rol) {
        return rolRepositorio.save(rol);
    }

    public List<Rol> obtenerTodos() {
        return rolRepositorio.findAll();
    }

    public Optional<Rol> obtenerPorId(Integer id) {
        return rolRepositorio.findById(id);
    }

    public void eliminarRol(Integer id) {
        rolRepositorio.deleteById(id);
    }
}