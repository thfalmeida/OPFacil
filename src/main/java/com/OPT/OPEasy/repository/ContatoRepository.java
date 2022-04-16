package com.OPT.OPEasy.repository;

import java.util.Optional;

import com.OPT.OPEasy.model.Contato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
     
   // @Query("Select u from Contato where u.nick = :nick")
    Optional<Contato> findByNick(@Param("nick") String nick);

}
