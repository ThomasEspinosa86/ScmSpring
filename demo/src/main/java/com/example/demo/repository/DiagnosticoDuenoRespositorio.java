package com.example.demo.repository;

import modelo.DiagnosticoDueno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoDuenoRespositorio extends JpaRepository<DiagnosticoDueno, Long> {
    DiagnosticoDueno FindById (int IdD);
}
