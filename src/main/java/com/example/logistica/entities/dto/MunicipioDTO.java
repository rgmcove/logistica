package com.example.logistica.entities.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDTO {

    private Integer id;
    private String nombre;
    private DepartamentoDTO departamento;
}
