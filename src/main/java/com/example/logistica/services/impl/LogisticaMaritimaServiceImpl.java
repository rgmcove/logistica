package com.example.logistica.services.impl;

import com.example.logistica.entities.*;
import com.example.logistica.entities.dto.LogisticaMaritimaDTO;
import com.example.logistica.entities.views.LogisticaMaritimaViews;
import com.example.logistica.repository.*;
import com.example.logistica.services.LogisticaMaritimaService;
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
public class LogisticaMaritimaServiceImpl implements LogisticaMaritimaService {

    @Autowired
    private LogisticaMaritimaRepository logisticaMaritimaRepository;
    @Autowired
    private TipoProductoRepository tipoProductoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PuertoRepository puertoRepository;
    @Autowired
    private FlotaRepository flotaRepository;

    private final ModelMapper modelMapper;

    public LogisticaMaritimaServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LogisticaMaritimaDTO findById(Long id) {
        return modelMapper.map(logisticaMaritimaRepository.getById(id), LogisticaMaritimaDTO.class);
    }

    @Override
    public LogisticaMaritimaDTO findByGuia(String guia) {
        return modelMapper.map(logisticaMaritimaRepository.findByGuia(guia), LogisticaMaritimaDTO.class);
    }

    @Override
    public List<LogisticaMaritimaDTO> findByCliente(Long cliente) {
        List<LogisticaMaritima> logisticaMaritimas = logisticaMaritimaRepository.findByCliente(cliente);
        return logisticaMaritimas.stream().map(logisticaMaritima -> modelMapper.map(logisticaMaritima, LogisticaMaritimaDTO.class))
                .collect(toList());
    }

    @Override
    public List<LogisticaMaritimaDTO> findByFlota(String numeroflota) {
        List<LogisticaMaritima> logisticaMaritimas = logisticaMaritimaRepository.findByFlota(numeroflota);
        return logisticaMaritimas.stream().map(logisticaMaritima -> modelMapper.map(logisticaMaritima, LogisticaMaritimaDTO.class))
                .collect(toList());
    }

    @Override
    public Page<LogisticaMaritimaDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<LogisticaMaritima> logisticaMaritimas = logisticaMaritimaRepository.findAll();
        List<LogisticaMaritimaDTO> logisticaMaritimaDTOS = logisticaMaritimas.stream().map(logisticaMaritima -> modelMapper
                        .map(logisticaMaritima, LogisticaMaritimaDTO.class))
                .collect(java.util.stream.Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), logisticaMaritimas.size());
        return new PageImpl<LogisticaMaritimaDTO>(logisticaMaritimaDTOS.subList(start, end), pageable, logisticaMaritimas.size());
    }

    @Override
    public LogisticaMaritimaDTO save(LogisticaMaritimaViews logisticaMaritimaViews) {
        TipoProducto tipoProducto = tipoProductoRepository.getById(logisticaMaritimaViews.getTipoProducto());
        Flota flota = flotaRepository.getById(logisticaMaritimaViews.getFlota());
        Puerto puerto = puertoRepository.getById(logisticaMaritimaViews.getPuertos());
        Cliente cliente = clienteRepository.getById(logisticaMaritimaViews.getClientes());
        LogisticaMaritima logisticaMaritima = modelMapper.map(logisticaMaritimaViews, LogisticaMaritima.class);
        logisticaMaritima.setTipoProducto(tipoProducto);
        logisticaMaritima.setFlota(flota);
        logisticaMaritima.setPuertos(puerto);
        logisticaMaritima.setClientes(cliente);
        if (logisticaMaritimaViews.getCantidad() > 10) {
            Double precio = logisticaMaritimaViews.getPrecioEnvio().doubleValue();
            Double descuento = (precio * 3) / 100;
            BigDecimal precioNuevo = BigDecimal.valueOf(precio - descuento);
            logisticaMaritima.setPrecioEnvio(precioNuevo);
            logisticaMaritima.setDescuento(BigDecimal.valueOf(descuento));
            logisticaMaritima.setPrecioNormal(logisticaMaritimaViews.getPrecioEnvio());
        } else {
            logisticaMaritima.setPrecioNormal(logisticaMaritimaViews.getPrecioEnvio());
        }
        return modelMapper.map(logisticaMaritimaRepository.save(logisticaMaritima), LogisticaMaritimaDTO.class);
    }

    @Override
    public LogisticaMaritimaDTO update(LogisticaMaritimaViews logisticaMaritimaViews, Long id) {
        Optional<LogisticaMaritima> existLogistica = logisticaMaritimaRepository.findById(id);
        if (existLogistica.isPresent()) {
            TipoProducto tipoProducto = tipoProductoRepository.getById(logisticaMaritimaViews.getTipoProducto());
            Flota flota = flotaRepository.getById(logisticaMaritimaViews.getFlota());
            Puerto puerto = puertoRepository.getById(logisticaMaritimaViews.getPuertos());
            Cliente cliente = clienteRepository.getById(logisticaMaritimaViews.getClientes());
            LogisticaMaritima logisticaMaritima = modelMapper.map(logisticaMaritimaViews, LogisticaMaritima.class);
            logisticaMaritima.setTipoProducto(tipoProducto);
            logisticaMaritima.setFlota(flota);
            logisticaMaritima.setPuertos(puerto);
            logisticaMaritima.setClientes(cliente);
            logisticaMaritima.setId(id);
            if (logisticaMaritimaViews.getCantidad() > 10) {
                Double precio = logisticaMaritimaViews.getPrecioEnvio().doubleValue();
                Double descuento = (precio * 3) / 100;
                BigDecimal precioNuevo = BigDecimal.valueOf(precio - descuento);
                logisticaMaritima.setPrecioEnvio(precioNuevo);
                logisticaMaritima.setDescuento(BigDecimal.valueOf(descuento));
                logisticaMaritima.setPrecioNormal(logisticaMaritimaViews.getPrecioEnvio());
            } else {
                logisticaMaritima.setPrecioNormal(logisticaMaritimaViews.getPrecioEnvio());
            }
            return modelMapper.map(logisticaMaritimaRepository.save(logisticaMaritima), LogisticaMaritimaDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        logisticaMaritimaRepository.deleteById(id);
    }

}

