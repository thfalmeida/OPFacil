package com.OPT.OPEasy.repository;

import java.util.Optional;

import com.OPT.OPEasy.model.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
    
    public Optional<Empresa> findByNick(String nick);
}
