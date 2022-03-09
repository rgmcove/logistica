package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class DepartamentoDTO implements Serializable {
    @NotNull(message = "El id del departamento no puede ser nulo")
    private final Integer id;
    @NotNull(message = "El nombre del departamento no puede ser nulo")
    @Size(max = 150, message = "El nombre del departamento no puede tener mas de 150 caracteres")
    private final String nombre;
    @NotNull(message = "El pais del departamento no puede ser nulo")
    private final PaisDTO pais;
}
