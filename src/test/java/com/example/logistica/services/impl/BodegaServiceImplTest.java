package com.example.logistica.services.impl;

import com.example.logistica.entities.Bodegas;
import com.example.logistica.entities.Municipio;
import com.example.logistica.entities.dto.BodegasDTO;
import com.example.logistica.entities.views.BodegasViews;
import com.example.logistica.repository.BodegasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BodegaServiceImplTest {

    @Mock
    BodegasRepository bodegasRepository;

    @InjectMocks
    private BodegaServiceImpl bodegaService;

    private Bodegas bodegas;
    private BodegasDTO bodegasDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bodegas = new Bodegas();
        bodegas.setId(new Long(1));
        bodegas.setNombre("Bodega 1");
        bodegas.setDireccion("Calle 1");
        bodegas.setTelefono("123456789");
        bodegas.setMunicipio(new Municipio());

        when(bodegasRepository.getById(1L)).thenReturn(bodegas);

    }

    @Test
    void findById() {
        bodegasDTO = bodegaService.findById(1L);
        assertThat(bodegasDTO.getNombre()).isEqualTo(bodegas.getNombre());
    }

}