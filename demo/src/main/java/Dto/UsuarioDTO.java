package Dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;
    private Integer idRol;
}
