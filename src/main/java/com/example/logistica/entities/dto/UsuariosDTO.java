package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class UsuariosDTO implements Serializable {
    @NotNull(message = "El id de usuario no puede ser nulo")
    private final Long idUsuario;
    @NotNull(message = "El documento no puede ser nulo")
    @Size(max = 15, message = "El documento no puede tener mas de 15 caracteres")
    private final String documento;
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(max = 45, message = "El nombre no puede tener mas de 45 caracteres")
    private final String nombres;
    @NotNull(message = "El apellido no puede ser nulo")
    @Size(max = 45, message = "El apellido no puede tener mas de 45 caracteres")
    private final String apellidos;
    @NotNull(message = "El email no puede ser nulo")
    @Email(message = "El email no es valido")
    @Size(max = 45, message = "El email no puede tener mas de 45 caracteres")
    private final String email;
    @Size(max = 15, message = "El telefono no puede ser mayor a 15 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "El telefono solo puede contener numeros")
    private final String telefono;
    @Size(max = 100, message = "La contraseña no puede tener mas de 100 caracteres")
    @NotNull(message = "El password no puede ser nulo")
    private final String password;
    private final Date fechaCreacion;
    private final RolesDTO fkRol;
    private final String token;
}
