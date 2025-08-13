package com.example.demo.repository;

import modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
    Rol FindById (int IdR);
}
