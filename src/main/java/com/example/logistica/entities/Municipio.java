package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "municipio")
@Data
@ToString
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio")
    @NotNull(message = "El id del municipio no puede ser nulo")
    private Integer id;

    @Column(name = "nombre")
    @NotNull(message = "El nombre del municipio no puede ser nulo")
    @Size(max = 45, message = "El nombre del municipio no puede tener mas de 45 caracteres")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_departamento")
    private Departamento departamento;

}