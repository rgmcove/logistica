package com.example.logistica.repository;

import com.example.logistica.entities.TipoLogistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLogisticaRepository extends JpaRepository<TipoLogistica, Integer> {
}