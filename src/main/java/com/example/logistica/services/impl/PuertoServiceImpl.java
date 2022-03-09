package com.example.logistica.services.impl;

import com.example.logistica.entities.Municipio;
import com.example.logistica.entities.Puerto;
import com.example.logistica.entities.dto.PuertoDTO;
import com.example.logistica.entities.views.PuertoViews;
import com.example.logistica.repository.MunicipioRepository;
import com.example.logistica.repository.PuertoRepository;
import com.example.logistica.services.PuertoService;
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
public class PuertoServiceImpl implements PuertoService {

    @Autowired
    private PuertoRepository puertoRepository;
    @Autowired
    private MunicipioRepository municipioRepository;

    private final ModelMapper modelMapper;

    public PuertoServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PuertoDTO findById(Long id) {
        return modelMapper.map(puertoRepository.findById(id), PuertoDTO.class);
    }

    @Override
    public Page<PuertoDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Puerto> puertos = puertoRepository.findAll();
        List<PuertoDTO> puertoDTOS = puertos.stream().map(puerto -> modelMapper.map(puerto, PuertoDTO.class))
                .collect(java.util.stream.Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), puertos.size());
        return new PageImpl<PuertoDTO>(puertoDTOS.subList(start, end), pageable, puertos.size());
    }

    @Override
    public PuertoDTO save(PuertoViews puertoViews) {
        Municipio municipio = municipioRepository.getById(puertoViews.getMunicipioId());
        Puerto puerto = modelMapper.map(puertoViews, Puerto.class);
        puerto.setMunicipio(municipio);
        return modelMapper.map(puertoRepository.save(puerto), PuertoDTO.class);
    }

    @Override
    public void delete(Long id) {
        puertoRepository.deleteById(id);
    }

    @Override
    public PuertoDTO update(PuertoViews puertoViews, Long id) {
        Optional<Puerto> existsPuerto = puertoRepository.findById(id);
        if (existsPuerto.isPresent()) {
            Puerto puerto = modelMapper.map(puertoViews, Puerto.class);
            puerto.setId(id);
            return modelMapper.map(puertoRepository.save(puerto), PuertoDTO.class);
        } else {
            return null;
        }
    }
}
