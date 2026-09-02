package com.estoque_admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReceitaIngredienteResponseDTO {

    private Long itemEstoqueId;

    private String nome;

    private BigDecimal quantidade;
}
