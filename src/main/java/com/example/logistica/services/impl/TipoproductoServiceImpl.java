package com.example.logistica.services.impl;

import com.example.logistica.entities.TipoLogistica;
import com.example.logistica.entities.TipoProducto;
import com.example.logistica.entities.dto.TipoProductoDTO;
import com.example.logistica.entities.views.TipoProductoViews;
import com.example.logistica.repository.TipoLogisticaRepository;
import com.example.logistica.repository.TipoProductoRepository;
import com.example.logistica.services.TipoProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoproductoServiceImpl implements TipoProductoService {

    @Autowired
    private TipoProductoRepository tipoProductoRepository;
    @Autowired
    private TipoLogisticaRepository tipoLogisticaRepository;

    private final ModelMapper modelMapper;

    public TipoproductoServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TipoProductoDTO findById(Integer id) {
        return modelMapper.map(tipoProductoRepository.findById(id), TipoProductoDTO.class);
    }

    @Override
    public Page<TipoProductoDTO> getAllTipoProducto(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<TipoProducto> tipoProductos = tipoProductoRepository.findAll();
        List<TipoProductoDTO> tipoProductoDTOS = tipoProductos.stream().map(tipoProducto -> modelMapper.map(tipoProducto, TipoProductoDTO.class))
                .collect(java.util.stream.Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), tipoProductos.size());
        return new PageImpl<>(tipoProductoDTOS.subList(start, end), pageable, tipoProductos.size());
    }

    @Override
    public TipoProductoDTO save(TipoProductoViews tipoProductoViews) {
        TipoLogistica tipoLogistica = tipoLogisticaRepository.getById(tipoProductoViews.getTipoLogisticaId());
        TipoProducto tipoProducto = modelMapper.map(tipoProductoViews, TipoProducto.class);
        tipoProducto.setTipoLogistica(tipoLogistica);
        return modelMapper.map(tipoProductoRepository.save(tipoProducto), TipoProductoDTO.class);
    }

    @Override
    public void delete(Integer id) {
        tipoProductoRepository.deleteById(id);
    }

    @Override
    public TipoProductoDTO update(TipoProductoViews tipoProductoViews, Integer id) {
        Optional<TipoProducto> exists = tipoProductoRepository.findById(id);
        if (exists.isPresent()) {
            TipoProducto tipoProducto    = modelMapper.map(tipoProductoViews, TipoProducto.class);
            tipoProducto.setId(id);
            return modelMapper.map(tipoProductoRepository.save(tipoProducto), TipoProductoDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public List<TipoProductoDTO> getTipoProductosByTerrestre() {
        List<TipoProducto> tipoProductos = tipoProductoRepository.findByTerrestre();
        List<TipoProductoDTO> tipoProductoDTOS = tipoProductos.stream().map(tipoProducto -> modelMapper.map(tipoProducto, TipoProductoDTO.class))
                .collect(java.util.stream.Collectors.toList());
        return tipoProductoDTOS;
    }

    @Override
    public List<TipoProductoDTO> getTipoProductosByMaritimo() {
        List<TipoProducto> tipoProductos = tipoProductoRepository.findByMaritimo();
        List<TipoProductoDTO> tipoProductoDTOS = tipoProductos.stream().map(tipoProducto -> modelMapper.map(tipoProducto, TipoProductoDTO.class))
                .collect(java.util.stream.Collectors.toList());
        return tipoProductoDTOS;
    }

}
