package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "ROLES")
@Entity
@Data
@ToString
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    @NotNull(message = "El id del rol no puede ser nulo")
    private Integer id;

    @Column(name = "descripcion")
    @NotNull(message = "La descripcion del rol no puede ser nulo")
    @Size(max = 45, message = "La descripcion del rol no puede tener mas de 45 caracteres")
    private String descripcion;

}
