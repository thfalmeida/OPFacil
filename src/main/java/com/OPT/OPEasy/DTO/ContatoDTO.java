package com.OPT.OPEasy.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ContatoDTO {
    private String nome, email, nick, descricao;
    private Long empresa;
}
