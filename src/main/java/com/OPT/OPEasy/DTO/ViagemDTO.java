package com.OPT.OPEasy.DTO;

import java.sql.Date;
import java.util.List;

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
    private Date data;
    private float valor, avaria;
    private List<TransporteDTO> transporte;

}
