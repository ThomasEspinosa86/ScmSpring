package com.example.demo.service;

import modelo.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioServicio extends JpaRepository<Veterinario, Long> {
    Veterinario FindById(int Id);
}
