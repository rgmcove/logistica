package com.example.logistica.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogisticaTerrestreDTO {

    private Long id;
    private Integer cantidad;
    private LocalDate fechaRegistro;
    private LocalDate fechaEntrega;
    private BigDecimal precioEnvio;
    private String numeroGuia;
    private TipoProductoDTO tipoProducto;
    private VehiculoDTO vehiculos;
    private BodegasDTO bodegas;
    private BigDecimal descuento;
    private BigDecimal precioNormal;
    private ClienteDTO clientes;
}
