package com.example.logistica.repository;

import com.example.logistica.entities.TipoProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {

    @Query("SELECT t FROM TipoProducto t WHERE t.tipoLogistica.id = 1")
    List<TipoProducto> findByTerrestre();

    @Query("SELECT t FROM TipoProducto t WHERE t.tipoLogistica.id = 2")
    List<TipoProducto> findByMaritimo();
}