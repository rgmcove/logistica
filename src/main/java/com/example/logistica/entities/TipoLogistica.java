package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tipo_logistica")
@Data
@ToString
public class TipoLogistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_logistica")
    @NotNull(message = "el id tipo de logística es obligatório")
    private Integer idLogistica;

    @Column(name = "descripcion")
    @NotNull(message = "la descripción es obligatória")
    @Size(max = 45, message = "la descripción no puede tener más de 45 caracteres")
    private String descripcion;

}