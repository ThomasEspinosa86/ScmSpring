package com.example.demo.repository;

import com.example.demo.modelo.ActividadFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadFisicaRepositorio extends JpaRepository<ActividadFisica, Integer> {
    List<ActividadFisica> findByTipoActividad(String tipoActividad);
}
