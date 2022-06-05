package com.OPT.OPEasy.DTO;

import java.util.List;

import com.OPT.OPEasy.model.Universo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransporteDTO {
    private Long transporte, mercadoId;
    private List<Universo> universo;

}
