package com.example.logistica.repository;

import com.example.logistica.entities.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    Optional<Municipio> findById(Integer integer);
}