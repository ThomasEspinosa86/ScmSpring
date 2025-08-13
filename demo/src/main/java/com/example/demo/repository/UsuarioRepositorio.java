package com.example.demo.repository;

import modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    Usuario FindByEmail(String Email);
}
