package com.example.demo.repository;

import com.example.demo.modelo.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Integer> {
    List<Cita> findByEstadoCita(String estado);
}
