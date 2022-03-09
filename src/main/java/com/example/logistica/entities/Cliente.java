package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clientes")
@Data
@ToString
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Long id;

    @Column(name = "nit")
    @NotNull(message = "El nit es obligatorio")
    @Size(max = 15, message = "El nit no puede tener mas de 15 caracteres")
    private String nit;

    @Column(name = "razon_social")
    @NotNull(message = "La razon social es obligatoria")
    @Size(max = 100, message = "La razon social no puede tener mas de 100 caracteres")
    private String razonSocial;

    @Column(name = "direccion")
    @Size(max = 45, message = "La direccion no puede tener mas de 45 caracteres")
    private String direccion;

    @Column(name = "telefono")
    @Size(max = 15, message = "El telefono no puede tener mas de 45 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "El telefono solo puede contener numeros")
    private String telefono;

    @Column(name = "email", length = 45)
    @Size(max = 45, message = "El email no puede tener mas de 45 caracteres")
    @Email(message = "El email no es valido")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_municipio")
    private Municipio municipio;
}