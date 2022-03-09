package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "puertos")
@Data
@ToString
public class Puerto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_puerto")
    private Long id;

    @Column(name = "direccion")
    @NotNull(message = "La dirección no puede ser nula")
    @Size(max = 45, message = "La dirección no puede tener más de 45 caracteres")
    private String direccion;

    @Column(name = "telefono")
    @NotNull(message = "El teléfono no puede ser nulo")
    @Size(max = 15, message = "El teléfono no puede tener más de 15 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "El telefono solo puede contener numeros")
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_municipio")
    private Municipio municipio;

}