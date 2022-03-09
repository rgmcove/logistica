package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bodegas")
@Data
@ToString
public class Bodegas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bodega", nullable = false)
    private Long id;

    @Column(name = "direccion")
    @NotNull(message = "La dirección no puede estar vacía")
    @Size(max = 45, message = "La dirección no puede tener más de 45 caracteres")
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 45)
    @NotNull(message = "El teléfono no puede estar vacío")
    @Size(max = 45, message = "El teléfono no puede tener más de 45 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "El teléfono debe contener solo números")
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_municipio")
    private Municipio municipio;

}