package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class TipoProductoDTO implements Serializable {
    @NotNull(message = "El id del tipo de producto no puede ser nulo")
    private final Integer id;
    @NotNull(message = "La descripcion del tipo de producto no puede ser nula")
    @Size(max = 300, message = "La descripcion del tipo de producto no puede tener mas de 300 caracteres")
    private final String descripcion;
    private final TipoLogisticaDTO tipoLogistica;
}
