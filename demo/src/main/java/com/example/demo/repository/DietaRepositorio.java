package com.example.demo.repository;

import com.example.demo.modelo.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietaRepositorio extends JpaRepository<Dieta, Integer> {
    List<Dieta> findByTipoDieta(String tipoDieta);
}
