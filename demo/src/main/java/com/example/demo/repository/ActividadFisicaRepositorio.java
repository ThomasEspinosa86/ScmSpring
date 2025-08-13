package com.example.demo.repository;

import modelo.ActividadFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadFisicaRepositorio extends JpaRepository<ActividadFisica, Long> {
    ActividadFisica findById (int IdF);
}
