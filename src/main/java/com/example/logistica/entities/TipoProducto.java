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
    private Long idProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_logistica")
    private TipoLogistica tipoLogistica;

}