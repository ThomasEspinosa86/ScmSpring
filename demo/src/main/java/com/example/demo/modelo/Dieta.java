package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dieta")
@Data
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDi")
    private Integer idDi;

    @ManyToOne
    @JoinColumn(name = "IdM", referencedColumnName = "IdM")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "IdV", referencedColumnName = "IdV")
    private Veterinario veterinario;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "TipoDieta")
    private String tipoDieta;
}
