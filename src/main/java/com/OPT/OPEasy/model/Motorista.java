package com.OPT.OPEasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Motorista {
    @SequenceGenerator(name="motoristaGenerator", allocationSize=1)
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="motoristaGenerator")
    private Long id;
    private String nick, telefone, cpf, nome;

    public void setAttributes(Motorista motorista){
        if(motorista.getNick() != null)
            this.nick = motorista.getNick();
        if(motorista.getTelefone() != null)
            this.telefone = motorista.getTelefone();
        if(motorista.getCpf() != null)
            this.cpf = motorista.getCpf();
        if(motorista.getNome() != null)
            this.nome = motorista.getNome();
    }
}
