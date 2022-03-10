package com.example.logistica.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "bodegas")
@Data
public class Bodegas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bodega", nullable = false)
    private Long id;

    @Column(name = "nombre")

    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 45)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "fk_id_municipio")
    private Municipio municipio;

}