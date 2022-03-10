package com.example.logistica.entities.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class ClienteViews {
    @NotNull(message = "El nit es obligatorio")
    @Size(max = 15, message = "El nit no puede tener mas de 15 caracteres")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "El nit solo puede contener letras y numeros, ingreselo sin numero de verificacion")
    private String nit;
    @NotNull(message = "La razon social es obligatoria")
    @Size(max = 100, message = "La razon social no puede tener mas de 100 caracteres")
    private String razonSocial;
    @Size(max = 45, message = "La direccion no puede tener mas de 45 caracteres")
    private String direccion;
    @Size(max = 15, message = "El teléfono no puede tener más de 15 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "El telefono solo puede contener numeros")
    private String telefono;
    @Size(max = 45, message = "El email no puede tener mas de 45 caracteres")
    @Email(message = "El email no es valido")
    private String email;
    @NotNull(message = "El id del municipio no puede ser nulo")
    private Integer municipioId;
}
