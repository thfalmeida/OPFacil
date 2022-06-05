package com.OPT.OPEasy.repository;

import java.util.List;
import java.util.Optional;

import com.OPT.OPEasy.model.Transporte;
import com.OPT.OPEasy.model.Universo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UniversoRepository extends JpaRepository<Universo, Long>{
     
 
    // @Query("Select u from Universo where u.order = :order")
    Optional<Universo> findByOrdem(String order);

    // List<Universo> findByIdMercado(Long idMercado);

    List<Universo> findByTransporte(Transporte transporte);
}
