package com.OPT.OPEasy.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
public class Viagem {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "sequence_id_viagem"
    )
    private Long id;
    private Long motoristaID, empresaID, valoresID;
    String endereco;
    LocalDate data;


    public void setAttributes(Viagem viagem){
        if(viagem.getMotoristaID() != null)
            this.motoristaID = viagem.getMotoristaID();
        if(viagem.getEmpresaID() != null)
            this.empresaID= viagem.getEmpresaID();
        if(viagem.getData()!= null)
            this.data = viagem.getData();
        if(viagem.getValoresID() != null)
            this.valoresID = viagem.getValoresID();
        if(viagem.getEndereco() != null)
            this.endereco = viagem.getEndereco();

    }

}
