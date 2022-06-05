package com.OPT.OPEasy.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.OPT.OPEasy.DTO.TransporteDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints =  @UniqueConstraint(columnNames = "transporte"))
@NoArgsConstructor

public class Transporte {
    // @Id
    // @SequenceGenerator(name="transporteGenerator", allocationSize = 1)
	// @GeneratedValue(generator="transporteGenerator", strategy=GenerationType.SEQUENCE)
    // private Long id;
    @Id
    private Long transporte;
    @ManyToOne
    private Mercado mercado;

    public void SetTransporte(Transporte transporte){
        if(transporte.getTransporte() != null)
            this.transporte = transporte.getTransporte();
        if(transporte.getMercado() != null)
            this.mercado = transporte.getMercado();
    }

    public void SetTransporte(TransporteDTO transporte){
        if(transporte.getTransporte() != null)
            this.transporte = transporte.getTransporte();
    }

}
