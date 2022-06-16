package com.OPT.OPEasy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OPT.OPEasy.model.Transporte;

public interface TransporteRepository extends JpaRepository<Transporte, Long> {

    public Optional<Transporte> findById(Long id);
    public Optional<Transporte> findByTransporte(Long transporte);
    
}
