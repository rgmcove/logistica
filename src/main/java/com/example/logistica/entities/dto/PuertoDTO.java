package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class PuertoDTO implements Serializable {
    private final Long id;
    @NotNull(message = "La dirección no puede ser nula")
    @Size(max = 45, message = "La dirección no puede tener más de 45 caracteres")
    private final String direccion;
    @NotNull(message = "El teléfono no puede ser nulo")
    @Size(max = 15, message = "El teléfono no puede tener más de 15 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "El telefono solo puede contener numeros")
    private final String telefono;
    private final MunicipioDTO municipio;
}
