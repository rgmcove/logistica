package com.example.logistica.services;

import com.example.logistica.entities.dto.TipoProductoDTO;
import com.example.logistica.entities.views.TipoProductoViews;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TipoProductoService {
    TipoProductoDTO findById(Long id);

    Page<TipoProductoDTO> getAllTipoProducto(int page, int size);

    TipoProductoDTO save(TipoProductoViews tipoProductoViews);

    void delete(Long id);

    TipoProductoDTO update(TipoProductoViews tipoProductoViews, Long id);

    List<TipoProductoDTO> getTipoProductosByTerrestre();

    List<TipoProductoDTO> getTipoProductosByMaritimo();
}
