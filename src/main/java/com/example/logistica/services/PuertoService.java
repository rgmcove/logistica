package com.example.logistica.services;

import com.example.logistica.entities.dto.PuertoDTO;
import com.example.logistica.entities.views.PuertoViews;
import org.springframework.data.domain.Page;

public interface PuertoService {
    PuertoDTO findById(Long id);

    Page<PuertoDTO> findAll(int page, int size);

    PuertoDTO save(PuertoViews puertoViews);

    void delete(Long id);

    PuertoDTO update(PuertoViews puertoViews, Long id);
}
