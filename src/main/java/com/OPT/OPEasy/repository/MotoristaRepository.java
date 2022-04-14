package com.OPT.OPEasy.repository;

import com.OPT.OPEasy.model.Motorista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    
}
