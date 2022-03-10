package com.example.logistica.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {

    private int codigo;
    private String mensaje;
    private T data;

    public ResponseDTO(int codigo, T data){
        this.codigo = codigo;
        this.data = data;
    }

    public ResponseDTO(int codigo, String mensaje){
        this.codigo=codigo;
        this.mensaje=mensaje;
    }

    public ResponseDTO(int codigo, T data, String mensaje){
        this.codigo = codigo;
        this.data = data;
        this.mensaje=mensaje;
    }
}
