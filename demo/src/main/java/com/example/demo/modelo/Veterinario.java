package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "veterinario")
@Data
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdV")
    private Integer idV;

    @Column(name = "Especialidad")
    private String especialidad;

    @OneToOne
    @JoinColumn(name = "IdU", referencedColumnName = "IdU")
    private Usuario usuario;
}
