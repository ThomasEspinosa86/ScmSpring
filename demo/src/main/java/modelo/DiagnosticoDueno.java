package modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "diagnosticodueno")
@Data
public class DiagnosticoDueno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdD")
    private Integer idD;

    @ManyToOne
    @JoinColumn(name = "IdM", referencedColumnName = "IdM")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "IdV", referencedColumnName = "IdV")
    private Veterinario veterinario;

    @Column(name = "FechaDiagnostico")
    private Date fechaDiagnostico;

    @Column(name = "Observaciones")
    private String observaciones;
}
