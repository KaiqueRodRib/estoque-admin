package com.estoque_admin.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReceitaRequestDTO {

    private String nome;

    private List<ReceitaIngredienteRequestDTO> ingredientes;

}
