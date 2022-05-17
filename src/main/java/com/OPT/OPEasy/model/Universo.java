package com.OPT.OPEasy.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Universo {

    @SequenceGenerator(name="universoGenerator", allocationSize=1)
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="universoGenerator")
    private Long id;
    private Long idMercado;
    private String ordem;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date data;
    private String valor;
    private String dif;

    public void setAttributes(Universo universo){
        if(universo.getIdMercado() != null)
            this.idMercado = universo.getIdMercado();
        if(universo.getOrdem() != null)
            this.ordem = universo.getOrdem();
        if(universo.getData() != null)
            this.data = universo.getData();
        if(universo.getValor() != null)
            this.valor = universo.valor;
        if(universo.getDif() != null)
            this.dif = universo.dif;
    }
    
}
