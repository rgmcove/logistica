package com.example.logistica.entities.views;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class TipoProductoViews implements Serializable {
    @NotNull(message = "La descripcion del tipo de producto no puede ser nula")
    @Size(max = 300, message = "La descripcion del tipo de producto no puede tener mas de 300 caracteres")
    private final String descripcion;
    @NotNull(message = "el id tipo de logística es obligatório")
    private final Integer tipoLogisticaId;
}
