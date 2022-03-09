package com.example.logistica.services;

import com.example.logistica.entities.dto.ClienteDTO;
import com.example.logistica.entities.views.ClienteViews;
import org.springframework.data.domain.Page;

public interface ClienteService {
    ClienteDTO findById(Long id);

    Page<ClienteDTO> findAll(int page, int size);

    ClienteDTO save(ClienteViews clienteViews);

    void delete(Long id);

    ClienteDTO update(ClienteViews clienteViews, Long id);
}
