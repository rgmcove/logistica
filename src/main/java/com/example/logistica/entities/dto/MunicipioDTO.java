package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class MunicipioDTO implements Serializable {
    @NotNull(message = "El id del municipio no puede ser nulo")
    private final Integer id;
    @NotNull(message = "El nombre del municipio no puede ser nulo")
    @Size(max = 45, message = "El nombre del municipio no puede tener mas de 45 caracteres")
    private final String nombre;
}
