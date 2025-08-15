package com.example.demo.repository;

import com.example.demo.modelo.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, Integer> {
    Mascota findByNombre(String nombre);
}
