package Dto;

import lombok.Data;

@Data
public class ActividadFisicaDTO {
    private String descripcion;
    private String tipoActividad;
    private Integer idMascota;
    private Integer idVeterinario;
}
