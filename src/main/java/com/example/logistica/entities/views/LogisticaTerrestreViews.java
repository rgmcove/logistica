package com.example.logistica.entities.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class LogisticaTerrestreViews {

    @NotNull(message = "La cantidad no puede ser nula")
    private Integer cantidad;
    @NotNull(message = "La fecha de registro no puede ser nula")
    private LocalDate fechaRegistro;
    @NotNull(message = "La fecha de entrega no puede ser nula")
    private LocalDate fechaEntrega;
    @NotNull(message = "El precio de envio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 15, fraction = 2)
    private BigDecimal precioEnvio;
    @NotNull(message = "El numero de guia no puede ser nulo")
    @Size(max = 10, message = "El numero de guia no puede tener mas de 10 caracteres")
    private String numeroGuia;
    @NotNull(message = "El id del tipo de producto no puede ser nulo")
    private Long tipoProducto;
    @NotNull(message = "El id del vehiculo es requerido")
    private Long vehiculos;
    private Long bodegas;
    private Long clientes;
}
