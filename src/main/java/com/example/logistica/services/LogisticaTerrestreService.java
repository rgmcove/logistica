package com.example.logistica.services;

import com.example.logistica.entities.dto.LogisticaTerrestreDTO;
import com.example.logistica.entities.views.LogisticaTerrestreViews;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LogisticaTerrestreService {
    LogisticaTerrestreDTO findById(Long id);

    LogisticaTerrestreDTO findByGuia(String guia);

    List<LogisticaTerrestreDTO> findByCliente(Long cliente);

    List<LogisticaTerrestreDTO> findByVehiculo(String placa);

    Page<LogisticaTerrestreDTO> findAll(int page, int size);

    LogisticaTerrestreDTO save(LogisticaTerrestreViews logisticaTerrestreViews);

    LogisticaTerrestreDTO update(LogisticaTerrestreViews logisticaTerrestreViews, Long id);

    void delete(Long id);
}
