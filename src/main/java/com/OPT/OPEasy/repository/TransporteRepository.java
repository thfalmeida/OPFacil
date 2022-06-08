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

    // @Query(value = "select t.viagem.getId() as Viagem, t.transporte as TRANSPORTE,t.mercado.getNome() as mercadoNome,t.viagem.getData() as data,.viagem.getMotorista().getNome() FROM transporte t;")
    // public List<Object> generateReport();
}
