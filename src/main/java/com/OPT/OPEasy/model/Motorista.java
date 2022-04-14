package com.OPT.OPEasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Motorista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nick, telefone, cpf, placaCaminhao;

    public void setAttributes(Motorista motorista){
        if(motorista.getNick() != null)
            this.nick = motorista.getNick();
        if(motorista.getTelefone() != null)
            this.telefone = motorista.getTelefone();
        if(motorista.getCpf() != null)
            this.cpf = motorista.getCpf();
        if(motorista.getPlacaCaminhao() != null)
            this.placaCaminhao = motorista.getPlacaCaminhao();
    }
}
