package com.example.logistica.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "municipio" , schema = "logistica")
@Getter
@Setter
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio")
    private Integer id;

    @Column(name = "nombre")
    @NotNull(message = "El nombre del municipio no puede ser nulo")
    @Size(max = 45, message = "El nombre del municipio no puede tener mas de 45 caracteres")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "fk_departamento")
    private Departamento departamento;

}