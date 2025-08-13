package modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "cita")
@Data
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdC")
    private Integer idC;

    @ManyToOne
    @JoinColumn(name = "IdM", referencedColumnName = "IdM")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "IdV", referencedColumnName = "IdV")
    private Veterinario veterinario;

    @Column(name = "FechaCita")
    private Date fechaCita;

    @Column(name = "MotivoCita")
    private String motivoCita;

    @Column(name = "EstadoCita")
    private String estadoCita;

    @ManyToOne
    @JoinColumn(name = "IdD", referencedColumnName = "IdD")
    private DiagnosticoDueno diagnostico;
}