package com.example.logistica.services.impl;

import com.example.logistica.entities.Bodegas;
import com.example.logistica.entities.Municipio;
import com.example.logistica.entities.dto.BodegasDTO;
import com.example.logistica.entities.views.BodegasViews;
import com.example.logistica.repository.BodegasRepository;
import com.example.logistica.repository.MunicipioRepository;
import com.example.logistica.services.BodegasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegaServiceImpl implements BodegasService {

    @Autowired
    private BodegasRepository bodegasRepository;
    @Autowired
    private MunicipioRepository municipioRepository;

    private final ModelMapper modelMapper;

    public BodegaServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BodegasDTO findById(Long id) {
        return modelMapper.map(bodegasRepository.findById(id), BodegasDTO.class);
    }

    @Override
    public Page<BodegasDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Bodegas> bodegas = bodegasRepository.findAll();
        List<BodegasDTO> bodegasDTOS = bodegas.stream().map(bodega -> modelMapper.map(bodega, BodegasDTO.class))
                .collect(java.util.stream.Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), bodegas.size());
        return new PageImpl<BodegasDTO>(bodegasDTOS.subList(start, end), pageable, bodegas.size());
    }

    @Override
    public BodegasDTO save(BodegasViews bodegasViews) {
        Municipio municipio = municipioRepository.getById(bodegasViews.getMunicipioId());
        Bodegas bodegas = modelMapper.map(bodegasViews, Bodegas.class);
        bodegas.setMunicipio(municipio);
        return modelMapper.map(bodegasRepository.save(bodegas), BodegasDTO.class);
    }

    @Override
    public void delete(Long id) {
        bodegasRepository.deleteById(id);
    }

    @Override
    public BodegasDTO update(BodegasViews bodegasViews, Long id) {
        Optional<Bodegas> existsBodegas = bodegasRepository.findById(id);
        if (existsBodegas.isPresent()) {
            Bodegas bodegas = modelMapper.map(bodegasViews, Bodegas.class);
            bodegas.setId(id);
            return modelMapper.map(bodegasRepository.save(bodegas), BodegasDTO.class);
        } else {
            return null;
        }
    }
}

