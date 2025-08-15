package com.example.demo.Dto;

import lombok.Data;
import java.util.Date;

@Data
public class DiagnosticoDuenoDTO {
    private Date fechaDiagnostico;
    private String observaciones;
    private Integer idMascota;
    private Integer idVeterinario;
}