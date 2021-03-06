package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "pais")
@Entity
@Data
@ToString
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    @NotNull(message = "El nombre del pais no puede ser nulo")
    @Size(max = 45, message = "El nombre del pais no puede tener mas de 45 caracteres")
    private String nombre;

}
