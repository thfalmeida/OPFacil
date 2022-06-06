package com.OPT.OPEasy.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViagemDTO {
    private Long motoristaID, empresaID;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private float valor, avaria;
    // private List<TransporteDTO> transporte;

}
