package com.OPT.OPEasy.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Viagem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    Motorista motorista;
    String endereco;
    LocalDate data;
    ValoresViagem valores;
    Empresa empresa;

}
