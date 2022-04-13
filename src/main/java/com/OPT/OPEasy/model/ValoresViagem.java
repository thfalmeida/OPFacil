package com.OPT.OPEasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ValoresViagem {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private float viagem, motorista, ajudantes, almoco, outros;
}
