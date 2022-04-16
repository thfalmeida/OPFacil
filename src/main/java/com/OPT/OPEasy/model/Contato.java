package com.OPT.OPEasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    @OneToOne
    private Empresa empresa;

public void setAttributes(Contato contato){
        if(contato.getNome() != null)
            this.nome = contato.getNome();
        if(contato.getEmail() != null)
            this.email = contato.getEmail();
        if(contato.getNick() != null)
            this.nick = contato.getNick();
        if(contato.getEmpresa() != null)
            this.empresa = contato.getEmpresa();
    }

    public String toString(){
        String text = "";
        text += nome + "\n" + email;
        return text;
    }
}
