package com.example.logistica.repository;

import com.example.logistica.entities.Flota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlotaRepository extends JpaRepository<Flota, Long> {
}