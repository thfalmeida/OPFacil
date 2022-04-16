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
@NoArgsConstructor
@Setter
@Getter
public class Empresa {
    @SequenceGenerator(name="empresaGenerator", allocationSize=1)
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="empresaGenerator")
    private Long id;
    private String cnpj, nick, razaoSocial, endereco;


    public void setAttributes(Empresa empresa){
        if(empresa.getCnpj() != null)
            this.cnpj = empresa.getCnpj();
        if(empresa.getNick() != null)
            this.nick = empresa.getNick();
        if(empresa.getEndereco() != null)
            this.endereco = empresa.getEndereco();
        if(empresa.getRazaoSocial() != null)
            this.razaoSocial = empresa.getRazaoSocial();
        
    }

    public String toString(){
        String text = "";
        text += razaoSocial + "\n" + nick;
        return text;
    }
}
