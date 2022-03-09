package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class TipoLogisticaDTO implements Serializable {
    @NotNull(message = "el id tipo de logística es obligatório")
    private final Integer id;
    @NotNull(message = "la descripción es obligatória")
    @Size(max = 45, message = "la descripción no puede tener más de 45 caracteres")
    private final String descripcion;
}
