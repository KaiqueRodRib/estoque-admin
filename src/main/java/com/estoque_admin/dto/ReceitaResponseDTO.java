package com.estoque_admin.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReceitaResponseDTO {

    private Long id;

    private String nome;

    private LocalDateTime dataCadastro;
}
