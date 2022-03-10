package com.example.logistica.entities.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;


import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class BodegasViews {

    @NotNull(message = "El nombre no puede ser nula")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;
    @NotNull(message = "La dirección no puede estar vacía")
    @Size(max = 45, message = "La dirección no puede tener más de 45 caracteres")
    private String direccion;
    @NotNull(message = "El teléfono no puede estar vacío")
    @Size(max = 15, message = "El teléfono no puede tener más de 15 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "El teléfono debe contener solo números")
    @NotBlank(message = "El teléfono no puede estar vacío")
    private String telefono;
    @NotNull(message = "El id del municipio no puede ser nulo")
    private Integer municipioId;
}
