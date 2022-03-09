package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LogisticaTerrestreDTO implements Serializable {
    private final Long id;
    @NotNull(message = "La cantidad no puede ser nula")
    private final Integer cantidad;
    @NotNull(message = "La fecha de registro no puede ser nula")
    private final LocalDate fechaRegistro;
    @NotNull(message = "La fecha de entrega no puede ser nula")
    private final LocalDate fechaEntrega;
    @NotNull(message = "El precio de envio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private final BigDecimal precioEnvio;
    @NotNull(message = "El numero de guia no puede ser nulo")
    @Size(max = 10, message = "El numero de guia no puede tener mas de 10 caracteres")
    private final String numeroGuia;
    private final TipoProductoDTO tipoProducto;
    private final VehiculoDTO vehiculos;
    private final BodegasDTO bodegas;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private final BigDecimal descuento;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private final BigDecimal precioNormal;
    private final ClienteDTO clientes;
}
