package com.example.logistica.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Table(name = "USUARIOS")
@Entity
@Data
@ToString
public class Usuarios {

    @Id
    @Column(name = "id_usuario")
    @NotNull(message = "El id de usuario no puede ser nulo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "documento")
    @NotNull(message = "El documento no puede ser nulo")
    @Size(max = 15, message = "El documento no puede tener mas de 15 caracteres")
    private String documento;

    @Column(name = "nombres")
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(max = 45, message = "El nombre no puede tener mas de 45 caracteres")
    private String nombres;

    @Column(name = "apellidos")
    @NotNull(message = "El apellido no puede ser nulo")
    @Size(max = 45, message = "El apellido no puede tener mas de 45 caracteres")
    private String apellidos;

    @Column(name = "email")
    @NotNull(message = "El email no puede ser nulo")
    @Email(message = "El email no es valido")
    @Size(max = 45, message = "El email no puede tener mas de 45 caracteres")
    private String email;

    @Column(name = "telefono")
    @Size(max = 15, message = "El telefono no puede ser mayor a 15 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "El telefono solo puede contener numeros")
    private String telefono;

    @Column(name = "password")
    @Size(max = 100, message = "La contraseña no puede tener mas de 100 caracteres")
    @NotNull(message = "El password no puede ser nulo")
    private String password;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_rol")
    private Roles fkRol;

    @Column(name = "token")
    private String token;

}
