package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class PaisDTO implements Serializable {
    @NotNull(message = "El id del pais no puede ser nulo")
    private final Integer id;
    @NotNull(message = "El nombre del pais no puede ser nulo")
    @Size(max = 45, message = "El nombre del pais no puede tener mas de 45 caracteres")
    private final String nombre;
    private final Integer codigo;
}
