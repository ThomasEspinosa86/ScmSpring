package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "actividadfisica")
@Data
public class        ActividadFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdF")
    private Integer idF;

    @ManyToOne
    @JoinColumn(name = "IdM", referencedColumnName = "IdM")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "IdV", referencedColumnName = "IdV")
    private Veterinario veterinario;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "TipoActividad")
    private String tipoActividad;
}
