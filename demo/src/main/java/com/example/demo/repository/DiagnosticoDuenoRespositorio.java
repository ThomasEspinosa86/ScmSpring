package com.example.demo.repository;

import com.example.demo.modelo.DiagnosticoDueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DiagnosticoDuenoRespositorio extends JpaRepository<DiagnosticoDueno, Integer> {
    List<DiagnosticoDueno> findByObservacionesContainingIgnoreCase(String observaciones);
}
