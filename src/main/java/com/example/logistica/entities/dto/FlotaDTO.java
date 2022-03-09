package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class FlotaDTO implements Serializable {
    private final Long id;
    @NotNull(message = "El número de flota es obligatorio")
    @Size(max = 8, message = "El número de flota no puede tener más de 8 caracteres")
    private final String numeroFlota;
}
