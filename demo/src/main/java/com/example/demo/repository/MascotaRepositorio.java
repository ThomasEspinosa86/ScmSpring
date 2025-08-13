package com.example.demo.repository;

import modelo.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepositorio extends JpaRepository<Mascota, Long> {
    Mascota FindById (int idM);
}
