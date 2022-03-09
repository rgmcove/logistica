package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "departamento")
@Data
@ToString
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento", nullable = false)
    @NotNull(message = "El id del departamento no puede ser nulo")
    private Integer id;

    @Column(name = "nombre")
    @NotNull(message = "El nombre del departamento no puede ser nulo")
    @Size(max = 150, message = "El nombre del departamento no puede tener mas de 150 caracteres")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pais")
    @NotNull(message = "El pais del departamento no puede ser nulo")
    private Pais pais;

}