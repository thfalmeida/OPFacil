package com.OPT.OPEasy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.OPT.OPEasy.DTO.UniversoDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Universo {

    @SequenceGenerator(name = "universoGenerator", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "universoGenerator")
    private Long id;
    @ManyToOne
    private Transporte transporte;
    private String ordem;
    private float valor, dif;

    public void setAttributes(Universo universo) {
        if(universo.getTransporte() != null)
            this.transporte = universo.getTransporte();
        if (universo.getOrdem() != null)
            this.ordem = universo.getOrdem();
        if (universo.getValor() != 0)
            this.valor = universo.getValor();
        if (universo.getDif() != 0)
            this.dif = universo.getDif();
    }

    public void setAttributes(UniversoDTO universo){
        if (universo.getOrdem() != null)
            this.ordem = universo.getOrdem();
        if (universo.getValor() != 0)
            this.valor = universo.getValor();
        if (universo.getDiff() != 0)
            this.dif = universo.getDiff();
    }
}
