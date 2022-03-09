package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class RolesDTO implements Serializable {
    @NotNull(message = "El id del rol no puede ser nulo")
    private final Integer id;
    @NotNull(message = "La descripcion del rol no puede ser nulo")
    @Size(max = 45, message = "La descripcion del rol no puede tener mas de 45 caracteres")
    private final String descripcion;
}
