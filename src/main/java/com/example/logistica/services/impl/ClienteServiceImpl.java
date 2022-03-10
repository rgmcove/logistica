package com.example.logistica.services.impl;

import com.example.logistica.entities.Cliente;
import com.example.logistica.entities.Municipio;
import com.example.logistica.entities.dto.ClienteDTO;
import com.example.logistica.entities.views.ClienteViews;
import com.example.logistica.repository.ClienteRepository;
import com.example.logistica.repository.MunicipioRepository;
import com.example.logistica.services.ClienteService;
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
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MunicipioRepository municipioRepository;

    private final ModelMapper modelMapper;

    public ClienteServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ClienteDTO findById(Long id) {
        return modelMapper.map(clienteRepository.getById(id), ClienteDTO.class);
    }

    @Override
    public Page<ClienteDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOS = clientes.stream().map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(java.util.stream.Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), clientes.size());
        return new PageImpl<ClienteDTO>(clienteDTOS.subList(start, end), pageable, clientes.size());
    }

    @Override
    public ClienteDTO save(ClienteViews clienteViews) {
        Municipio municipio = municipioRepository.getById(clienteViews.getMunicipioId());
        Cliente cliente = modelMapper.map(clienteViews, Cliente.class);
        cliente.setMunicipio(municipio);
        return modelMapper.map(clienteRepository.save(cliente), ClienteDTO.class);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteDTO update(ClienteViews clienteViews, Long id) {
        Optional<Cliente> existCliente = clienteRepository.findById(id);
        if (existCliente.isPresent()) {
            Cliente cliente = modelMapper.map(clienteViews, Cliente.class);
            cliente.setId(id);
            return modelMapper.map(clienteRepository.save(cliente), ClienteDTO.class);
        } else {
            return null;
        }
    }
}
