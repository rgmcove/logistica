package com.example.logistica.entities.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class TipoProductoViews {
    @NotNull(message = "La descripcion del tipo de producto no puede ser nula")
    @Size(max = 300, message = "La descripcion del tipo de producto no puede tener mas de 300 caracteres")
    private String descripcion;
    @NotNull(message = "el id tipo de logística es obligatório")
    private Integer tipoLogisticaId;
}
