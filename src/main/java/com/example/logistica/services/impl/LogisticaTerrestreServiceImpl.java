package com.example.logistica.services.impl;

import com.example.logistica.entities.*;
import com.example.logistica.entities.dto.LogisticaTerrestreDTO;
import com.example.logistica.entities.views.LogisticaTerrestreViews;
import com.example.logistica.repository.*;
import com.example.logistica.services.LogisticaTerrestreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class LogisticaTerrestreServiceImpl implements LogisticaTerrestreService {

    @Autowired
    private LogisticaTerrestreRepository logisticaTerrestreRepository;
    @Autowired
    private TipoProductoRepository tipoProductoRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private BodegasRepository bodegasRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;

    public LogisticaTerrestreServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LogisticaTerrestreDTO findById(Long id) {
        return modelMapper.map(logisticaTerrestreRepository.getById(id), LogisticaTerrestreDTO.class);
    }

    @Override
    public LogisticaTerrestreDTO findByGuia(String guia) {
        return modelMapper.map(logisticaTerrestreRepository.findByGuia(guia), LogisticaTerrestreDTO.class);
    }

    @Override
    public List<LogisticaTerrestreDTO> findByCliente(Long cliente) {
        List<LogisticaTerrestre> logisticaTerrestres = logisticaTerrestreRepository.findByCliente(cliente);
        return logisticaTerrestres.stream().map(logisticaTerrestre -> modelMapper.map(logisticaTerrestre, LogisticaTerrestreDTO.class))
                .collect(toList());
    }

    @Override
    public List<LogisticaTerrestreDTO> findByVehiculo(String placa) {
        List<LogisticaTerrestre> logisticaTerrestres = logisticaTerrestreRepository.findByVehiculo(placa);
        return logisticaTerrestres.stream().map(logisticaTerrestre -> modelMapper.map(logisticaTerrestre, LogisticaTerrestreDTO.class))
                .collect(toList());
    }

    @Override
    public Page<LogisticaTerrestreDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<LogisticaTerrestre> logisticaTerrestres = logisticaTerrestreRepository.findAll();
        List<LogisticaTerrestreDTO> logisticaTerrestreDTOS = logisticaTerrestres.stream().map(logisticaTerrestre -> modelMapper
                        .map(logisticaTerrestre, LogisticaTerrestreDTO.class))
                .collect(java.util.stream.Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), logisticaTerrestres.size());
        return new PageImpl<LogisticaTerrestreDTO>(logisticaTerrestreDTOS.subList(start, end), pageable, logisticaTerrestres.size());
    }

    @Override
    public LogisticaTerrestreDTO save(LogisticaTerrestreViews logisticaTerrestreViews) {
        TipoProducto tipoProducto = tipoProductoRepository.getById(logisticaTerrestreViews.getTipoProducto());
        Vehiculo vehiculo = vehiculoRepository.getById(logisticaTerrestreViews.getVehiculos());
        Bodegas bodegas = bodegasRepository.getById(logisticaTerrestreViews.getBodegas());
        Cliente cliente = clienteRepository.getById(logisticaTerrestreViews.getClientes());
        LogisticaTerrestre logisticaTerrestre = modelMapper.map(logisticaTerrestreViews, LogisticaTerrestre.class);
        logisticaTerrestre.setTipoProducto(tipoProducto);
        logisticaTerrestre.setVehiculos(vehiculo);
        logisticaTerrestre.setBodegas(bodegas);
        logisticaTerrestre.setClientes(cliente);
        if (logisticaTerrestreViews.getCantidad() > 10) {
            Double precio = logisticaTerrestreViews.getPrecioEnvio().doubleValue();
            Double descuento = (precio * 5) / 100;
            BigDecimal precioNuevo = BigDecimal.valueOf(precio - descuento);
            logisticaTerrestre.setPrecioEnvio(precioNuevo);
            logisticaTerrestre.setDescuento(BigDecimal.valueOf(descuento));
            logisticaTerrestre.setPrecioNormal(logisticaTerrestreViews.getPrecioEnvio());
        } else {
            logisticaTerrestre.setPrecioNormal(logisticaTerrestreViews.getPrecioEnvio());
        }
        return modelMapper.map(logisticaTerrestreRepository.save(logisticaTerrestre), LogisticaTerrestreDTO.class);
    }

    @Override
    public LogisticaTerrestreDTO update(LogisticaTerrestreViews logisticaTerrestreViews, Long id) {
        Optional<LogisticaTerrestre> existLogistica = logisticaTerrestreRepository.findById(id);
        if (existLogistica.isPresent()) {
            TipoProducto tipoProducto = tipoProductoRepository.getById(logisticaTerrestreViews.getTipoProducto());
            Vehiculo vehiculo = vehiculoRepository.getById(logisticaTerrestreViews.getVehiculos());
            Bodegas bodegas = bodegasRepository.getById(logisticaTerrestreViews.getBodegas());
            Cliente cliente = clienteRepository.getById(logisticaTerrestreViews.getClientes());
            LogisticaTerrestre logisticaTerrestre = modelMapper.map(logisticaTerrestreViews, LogisticaTerrestre.class);
            logisticaTerrestre.setTipoProducto(tipoProducto);
            logisticaTerrestre.setVehiculos(vehiculo);
            logisticaTerrestre.setBodegas(bodegas);
            logisticaTerrestre.setClientes(cliente);
            logisticaTerrestre.setId(id);
            if (logisticaTerrestreViews.getCantidad() > 10) {
                Double precio = logisticaTerrestreViews.getPrecioEnvio().doubleValue();
                Double descuento = (precio * 5) / 100;
                BigDecimal precioNuevo = BigDecimal.valueOf(precio - descuento);
                logisticaTerrestre.setPrecioEnvio(precioNuevo);
                logisticaTerrestre.setDescuento(BigDecimal.valueOf(descuento));
                logisticaTerrestre.setPrecioNormal(logisticaTerrestreViews.getPrecioEnvio());
            } else {
                logisticaTerrestre.setPrecioNormal(logisticaTerrestreViews.getPrecioEnvio());
            }
            return modelMapper.map(logisticaTerrestreRepository.save(logisticaTerrestre), LogisticaTerrestreDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        logisticaTerrestreRepository.deleteById(id);
    }

}
