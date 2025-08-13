package modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdU")
    private Integer idU;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "Correo")
    private String correo;

    @Column(name = "Contrasena")
    private String contrasena;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "IdR", referencedColumnName = "IdR")
    private Rol rol;

    @Column(name = "Foto")
    private String foto;
}