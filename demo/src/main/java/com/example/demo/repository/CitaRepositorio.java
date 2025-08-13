package com.example.demo.repository;

import modelo.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepositorio extends JpaRepository<Cita, Long> {
    Cita FindById(int idC);
}
