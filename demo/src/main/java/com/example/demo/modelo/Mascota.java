package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "mascotas")
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdM")
    private Integer idM;

    @ManyToOne
    @JoinColumn(name = "IdU", referencedColumnName = "IdU")
    private Usuario usuario;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Genero")
    private String genero;

    @Column(name = "FechaNacimineto")
    private Date fechaNacimiento;

    @Column(name = "Raza")
    private String raza;
}
