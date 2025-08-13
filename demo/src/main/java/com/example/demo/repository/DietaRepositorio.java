package com.example.demo.repository;

import modelo.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietaRepositorio extends JpaRepository<Dieta, Long> {
    Dieta FindById(int idDi);
}
