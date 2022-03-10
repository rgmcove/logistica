package com.example.logistica.entities.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BodegasDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private MunicipioDTO municipio;
}
