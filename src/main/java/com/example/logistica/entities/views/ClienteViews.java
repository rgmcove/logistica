package com.example.logistica.entities.views;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ClienteViews implements Serializable {
    @NotNull(message = "El nit es obligatorio")
    @Size(max = 15, message = "El nit no puede tener mas de 15 caracteres")
    private final String nit;
    @NotNull(message = "La razon social es obligatoria")
    @Size(max = 100, message = "La razon social no puede tener mas de 100 caracteres")
    private final String razonSocial;
    @Size(max = 45, message = "La direccion no puede tener mas de 45 caracteres")
    private final String direccion;
    @Size(max = 15, message = "El telefono no puede tener mas de 45 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "El telefono solo puede contener numeros")
    private final String telefono;
    @Size(max = 45, message = "El email no puede tener mas de 45 caracteres")
    @Email(message = "El email no es valido")
    private final String email;
    @NotNull(message = "El id del municipio no puede ser nulo")
    private final Integer municipioId;
}
