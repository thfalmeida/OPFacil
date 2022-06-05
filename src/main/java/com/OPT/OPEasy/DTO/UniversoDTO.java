package com.OPT.OPEasy.DTO;

import com.OPT.OPEasy.model.Universo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UniversoDTO {
    private Long transporteID;
    private String ordem;
    private float valor, diff;
    
    public void toDTO(Universo universo){
        if(universo.getTransporte() != null)
            this.transporteID = universo.getTransporte().getTransporte();
        if(universo.getOrdem() != null)
            this.ordem = universo.getOrdem();
        this.valor = universo.getValor();
        this.diff = universo.getDif();
    }
}
