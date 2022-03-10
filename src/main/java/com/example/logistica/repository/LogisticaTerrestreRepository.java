package com.example.logistica.repository;

import com.example.logistica.entities.LogisticaTerrestre;
import com.example.logistica.entities.dto.LogisticaTerrestreDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface LogisticaTerrestreRepository extends JpaRepository<LogisticaTerrestre, Long> {

    @Query("select a from LogisticaTerrestre a where a.numeroGuia =:guia")
    LogisticaTerrestre findByGuia(@Param("guia") String guia);

    @Query("select a from LogisticaTerrestre a where a.clientes.id =:cliente")
    List<LogisticaTerrestre> findByCliente(@Param("cliente") Long cliente);

    @Query("select a from LogisticaTerrestre a where a.vehiculos.placa =:placa")
    List<LogisticaTerrestre> findByVehiculo(@Param("placa") String placa);
}