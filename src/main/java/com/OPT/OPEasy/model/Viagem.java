package com.OPT.OPEasy.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.OPT.OPEasy.DTO.ViagemDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
public class Viagem {
    
    @SequenceGenerator(name="viagemGenerator", allocationSize=1)
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="viagemGenerator")
    private Long id;
    @ManyToOne
    private Motorista motorista;
    @ManyToOne
    private Empresa empresa;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private float valor, avaria;


    public void setAttributes(Viagem viagem){
        if(viagem.getMotorista() != null)
            this.motorista = viagem.getMotorista();
        if(viagem.getEmpresa() != null)
            this.empresa = viagem.getEmpresa();
        if(viagem.getData()!= null)
            this.data = viagem.getData();
        if(viagem.getData() != null)
            this.data = viagem.getData();
        if(viagem.getValor() != 0)
            this.valor = viagem.getValor();
        if(viagem.getAvaria() != 0)
        this.avaria = viagem.getAvaria();
    }

    public void setAttributes(ViagemDTO viagem){
        if(viagem.getData() != null)
            this.data = viagem.getData();
        if(viagem.getValor() != 0)
            this.valor = viagem.getValor();
        if(viagem.getAvaria() != 0)
        this.avaria = viagem.getAvaria();
    }

    public String ToString(Viagem viagem){
        String retorno  = viagem.getId() + "/" + viagem.getMotorista().getNick() + "/" + viagem.getValor();

        return retorno;
    }

}
