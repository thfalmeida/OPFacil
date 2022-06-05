package com.OPT.OPEasy.repository;

import java.util.List;

import com.OPT.OPEasy.model.Empresa;
import com.OPT.OPEasy.model.Motorista;
import com.OPT.OPEasy.model.Viagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long>{
    
    public List<Viagem> findByMotorista(Motorista motorista);

    public List<Viagem> findByEmpresa(Empresa empresa);
    
}
