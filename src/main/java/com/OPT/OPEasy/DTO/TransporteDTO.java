package com.OPT.OPEasy.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransporteDTO {
    private Long transporte, mercadoID, viagemID;
    private List<UniversoDTO> universo;

}
