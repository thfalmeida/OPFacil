package com.OPT.OPEasy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.OPT.OPEasy.DTO.TransporteDTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Transporte {
    @Id
    @SequenceGenerator(name="transporteGenerator", allocationSize = 1)
	@GeneratedValue(generator="transporteGenerator", strategy=GenerationType.SEQUENCE)
    private Long id;
    @Column(unique=true)
    private Long transporte;
    @ManyToOne
    private Mercado mercado;


    public void SetTransporteAttributes(Transporte transporte){
        if(transporte.getTransporte() != null)
            this.transporte = transporte.getTransporte();
        if(transporte.getMercado() != null)
            this.mercado = transporte.getMercado();
    }

    public void SetTransporteAttributes(TransporteDTO transporte){
        if(transporte.getTransporte() != null)
            this.transporte = transporte.getTransporte();
    }

}
