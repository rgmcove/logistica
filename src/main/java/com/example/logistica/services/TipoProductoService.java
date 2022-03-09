package com.example.logistica.services;

import com.example.logistica.entities.dto.TipoProductoDTO;
import com.example.logistica.entities.views.TipoProductoViews;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TipoProductoService {
    TipoProductoDTO findById(Integer id);

    Page<TipoProductoDTO> getAllTipoProducto(int page, int size);

    TipoProductoDTO save(TipoProductoViews tipoProductoViews);

    void delete(Integer id);

    TipoProductoDTO update(TipoProductoViews tipoProductoViews, Integer id);

    List<TipoProductoDTO> getTipoProductosByTerrestre();

    List<TipoProductoDTO> getTipoProductosByMaritimo();
}
