package com.OPT.OPEasy.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    private Long motoristaID, empresaID, mercadoID;
    String endereco;
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date data;
    float valor;


    public void setAttributes(Viagem viagem){
        if(viagem.getMotoristaID() != null)
            this.motoristaID = viagem.getMotoristaID();
        if(viagem.getEmpresaID() != null)
            this.empresaID= viagem.getEmpresaID();
        if(viagem.getData()!= null)
            this.data = viagem.getData();
        if(viagem.getMercadoID() != null)
            this.mercadoID = viagem.getMercadoID();
        if(viagem.getEndereco() != null)
            this.endereco = viagem.getEndereco();

    }

}
