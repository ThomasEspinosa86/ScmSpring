package Dto;

import lombok.Data;
import java.util.Date;

@Data
public class MascotaDTO {
    private String nombre;
    private String genero;
    private Date fechaNacimiento;
    private String raza;
    private Integer idUsuario;
}