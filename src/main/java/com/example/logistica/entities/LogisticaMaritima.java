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
    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    @Column(name = "fecha_registro")
    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate fechaRegistro;

    @Column(name = "fecha_entrega")
    @NotNull(message = "La fecha de entrega es obligatoria")
    private LocalDate fechaEntrega;

    @Column(name = "precio_envio", precision = 10)
    @NotNull(message = "El precio de envio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private BigDecimal precioEnvio;

    @Column(name = "numero_guia")
    @NotNull(message = "El numero de guia es obligatorio")
    @Size(max = 10, message = "El numero de guia no puede tener mas de 10 caracteres")
    private String numeroGuia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tipo_producto")
    private TipoProducto tipoProducto;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    @Column(name = "descuento", precision = 10)
    private BigDecimal descuento;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    @Column(name = "precio_normal", precision = 10)
    private BigDecimal precioNormal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_puerto")
    private Puerto puertos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_flota")
    private Flota flota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cliente")
    private Cliente clientes;

}