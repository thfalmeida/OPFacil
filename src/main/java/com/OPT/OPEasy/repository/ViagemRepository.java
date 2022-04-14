package com.OPT.OPEasy.repository;

import com.OPT.OPEasy.model.Viagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long>{
    
}
