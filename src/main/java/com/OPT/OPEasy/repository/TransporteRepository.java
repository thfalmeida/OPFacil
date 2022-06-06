package com.OPT.OPEasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OPT.OPEasy.model.Mercado;
import com.OPT.OPEasy.model.Transporte;
import com.OPT.OPEasy.model.Viagem;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, Long> {
    public List<Transporte> findByMercado(Mercado mercado);
    
    public List<Transporte> findByViagem(Viagem viagem);
}
