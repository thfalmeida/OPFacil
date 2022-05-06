package com.OPT.OPEasy.repository;

import java.util.Optional;

import com.OPT.OPEasy.model.Mercado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercadoRepository  extends JpaRepository<Mercado, Long>{

    public Optional<Mercado> findByNick(String nick);
}
