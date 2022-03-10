package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "logistica_maritima")
@Data
@ToString
public class LogisticaMaritima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private Long id;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name = "precio_envio", precision = 10)
    private BigDecimal precioEnvio;

    @Column(name = "numero_guia")
    private String numeroGuia;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_producto")
    private TipoProducto tipoProducto;

    @Column(name = "descuento", precision = 10)
    private BigDecimal descuento;

    @Column(name = "precio_normal", precision = 10)
    private BigDecimal precioNormal;

    @ManyToOne
    @JoinColumn(name = "fk_puerto")
    private Puerto puertos;

    @ManyToOne
    @JoinColumn(name = "fk_flota")
    private Flota flota;

    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente clientes;

}