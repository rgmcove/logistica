package com.example.logistica.services;

import com.example.logistica.entities.dto.LogisticaMaritimaDTO;
import com.example.logistica.entities.views.LogisticaMaritimaViews;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LogisticaMaritimaService {
    LogisticaMaritimaDTO findById(Long id);

    LogisticaMaritimaDTO findByGuia(String guia);

    List<LogisticaMaritimaDTO> findByCliente(Long cliente);

    List<LogisticaMaritimaDTO> findByFlota(String numeroflota);

    Page<LogisticaMaritimaDTO> findAll(int page, int size);

    LogisticaMaritimaDTO save(LogisticaMaritimaViews logisticaMaritimaViews);

    LogisticaMaritimaDTO update(LogisticaMaritimaViews logisticaMaritimaViews, Long id);

    void delete(Long id);
}
