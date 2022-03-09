package com.example.logistica.entities.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LogisticaMaritimaDTO implements Serializable {
    private final Long id;
    @NotNull(message = "La cantidad es obligatoria")
    private final Integer cantidad;
    @NotNull(message = "La fecha de registro es obligatoria")
    private final LocalDate fechaRegistro;
    @NotNull(message = "La fecha de entrega es obligatoria")
    private final LocalDate fechaEntrega;
    @NotNull(message = "El precio de envio es obligatorio")
    private final BigDecimal precioEnvio;
    @NotNull(message = "El numero de guia es obligatorio")
    @Size(max = 10, message = "El numero de guia no puede tener mas de 10 caracteres")
    private final String numeroGuia;
    private final TipoProductoDTO tipoProducto;
    private final BigDecimal descuento;
    private final BigDecimal precioNormal;
    private final PuertoDTO puertos;
    private final FlotaDTO flota;
    private final ClienteDTO clientes;
}
