package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehiculos")
@Data
@ToString
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    @NotNull(message = "El id del vehiculo es requerido")
    private Long id;

    @Column(name = "placa")
    @NotNull(message = "La placa del vehiculo es requerida")
    @Size(max = 6, message = "La placa del vehiculo debe tener 6 caracteres")
    private String placa;

}