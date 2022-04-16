package com.OPT.OPEasy.repository;

import java.util.List;

import com.OPT.OPEasy.model.Viagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long>{
    
    @Query("Select u from Viagem u where u.motoristaID = :id")
    public List<Viagem> findByMotorista(@Param("id") Long id);

    @Query("Select u from Viagem u where u.empresaID = :id")
    public List<Viagem> findByEmpresa(@Param("id") Long id);
    
}
