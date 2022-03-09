package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tipo_producto")
@Data
@ToString
public class TipoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_producto")
    @NotNull(message = "El id del tipo de producto no puede ser nulo")
    private Integer id;

    @Column(name = "descripcion")
    @NotNull(message = "La descripcion del tipo de producto no puede ser nula")
    @Size(max = 300, message = "La descripcion del tipo de producto no puede tener mas de 300 caracteres")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tipo_logistica")
    private TipoLogistica tipoLogistica;

}