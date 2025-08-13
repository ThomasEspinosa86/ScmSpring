package com.example.demo.repository;

import modelo.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepositorio extends JpaRepository<Veterinario, Long> {
    Veterinario FindById(int IdV);
}
