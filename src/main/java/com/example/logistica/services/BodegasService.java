package com.example.logistica.services;

import com.example.logistica.entities.dto.BodegasDTO;
import com.example.logistica.entities.views.BodegasViews;
import org.springframework.data.domain.Page;

public interface BodegasService {
    BodegasDTO findById(Long id);

    Page<BodegasDTO> findAll(int page, int size);

    BodegasDTO save(BodegasViews bodegasViews);

    void delete(Long id);

    BodegasDTO update(BodegasViews bodegasViews, Long id);
}
