package com.example.logistica.repository;

import com.example.logistica.entities.LogisticaMaritima;
import com.example.logistica.entities.LogisticaTerrestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticaMaritimaRepository extends JpaRepository<LogisticaMaritima, Long> {

    @Query("select a from LogisticaMaritima a where a.numeroGuia =:guia")
    LogisticaMaritima findByGuia(@Param("guia") String guia);

    @Query("select a from LogisticaMaritima a where a.clientes.id =:cliente")
    List<LogisticaMaritima> findByCliente(@Param("cliente") Long cliente);

    @Query("select a from LogisticaMaritima a where a.flota.numeroFlota =:flota")
    List<LogisticaMaritima> findByFlota(@Param("flota") String flota);
}