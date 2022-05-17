package com.OPT.OPEasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
public class Mercado {

    @SequenceGenerator(name="mercadoGenerator", allocationSize=1)
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mercadoGenerator")
    private Long id;
    private String nome;
    private String endereco;
    private String nick;

    public void setAttributes(Mercado mercado){
        if (mercado.getNome() != null)
            this.nome = mercado.getNome();
        if(mercado.getEndereco() != null)
            this.endereco = mercado.getEndereco();
        if(mercado.getNick() != null)
            this.nick = mercado.getNick();
    }
}
