package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "flota")
@Data
@ToString
public class Flota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flota")
    private Long id;

    @Column(name = "numero_flota")
    @NotNull(message = "El número de flota es obligatorio")
    @Size(max = 8, message = "El número de flota no puede tener más de 8 caracteres")
    private String numeroFlota;

}