package com.example.logistica.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
public class VehiculoDTO {

    @NotNull(message = "El id del vehiculo es requerido")
    private final Long id;
    @NotNull(message = "La placa del vehiculo es requerida")
    @Size(max = 6, message = "La placa del vehiculo debe tener 6 caracteres")
    private final String placa;
}
