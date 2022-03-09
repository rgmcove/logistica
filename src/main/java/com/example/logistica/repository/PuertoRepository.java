package com.example.logistica.repository;

import com.example.logistica.entities.Puerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuertoRepository extends JpaRepository<Puerto, Long> {
}