package com.OPT.OPEasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Contato {
    @SequenceGenerator(name="contatoGenerator", allocationSize=1)
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contatoGenerator")
    private Long id;
    private String nome, email;
    private String nick;
    @ManyToOne
    private Empresa empresa;
    private String descricao;

public void setAttributes(Contato contato){
        if(contato.getNome() != null)
            this.nome = contato.getNome();
        if(contato.getEmail() != null)
            this.email = contato.getEmail();
        if(contato.getNick() != null)
            this.nick = contato.getNick();
        if(contato.getEmpresa() != null)
            this.empresa = contato.getEmpresa();
        if(contato.getDescricao() != null)
            this.descricao = contato.getDescricao();
    }

    public static String toString(Contato contato){
        String text = "";
        text += contato.getNome() + "\n" + contato.getEmail();
        return text;
    }
}
