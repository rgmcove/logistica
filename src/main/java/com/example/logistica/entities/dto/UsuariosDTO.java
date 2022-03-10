package com.example.logistica.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosDTO {

    private Long idUsuario;
    private String documento;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String password;
    private Date fechaCreacion;
    private RolesDTO fkRol;
    private String token;
}
