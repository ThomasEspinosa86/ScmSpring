package com.example.demo.Dto;

import lombok.Data;
import java.util.Date;

@Data
public class CitaDTO {
    private Date fechaCita;
    private String motivoCita;
    private String estadoCita;
    private Integer idMascota;
    private Integer idVeterinario;
    private Integer idDiagnostico;
}
