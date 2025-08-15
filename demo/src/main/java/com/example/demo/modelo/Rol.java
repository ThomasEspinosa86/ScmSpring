package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdR")
    private Integer idR;

    @Column(name = "Rol")
    private String rol;
}