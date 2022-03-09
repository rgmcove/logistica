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
@Table(name = "logistica_terrestre")
@Data
@ToString
public class LogisticaTerrestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private Long id;

    @Column(name = "cantidad")
    @NotNull(message = "La cantidad no puede ser nula")
    private Integer cantidad;

    @Column(name = "fecha_registro")
    @NotNull(message = "La fecha de registro no puede ser nula")
    private LocalDate fechaRegistro;

    @Column(name = "fecha_entrega")
    @NotNull(message = "La fecha de entrega no puede ser nula")
    private LocalDate fechaEntrega;

    @Column(name = "precio_envio", precision = 10)
    @NotNull(message = "El precio de envio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private BigDecimal precioEnvio;

    @Column(name = "numero_guia")
    @NotNull(message = "El numero de guia no puede ser nulo")
    @Size(max = 10, message = "El numero de guia no puede tener mas de 10 caracteres")
    private String numeroGuia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tipo_producto")
    private TipoProducto tipoProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vehiculo")
    private Vehiculo vehiculos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_bodega")
    private Bodegas bodegas;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    @Column(name = "descuento", precision = 10)
    private BigDecimal descuento;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    @Column(name = "precio_normal", precision = 10)
    private BigDecimal precioNormal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cliente")
    private Cliente clientes;

}