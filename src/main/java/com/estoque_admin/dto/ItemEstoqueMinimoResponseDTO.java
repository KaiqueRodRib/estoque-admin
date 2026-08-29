package com.estoque_admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemEstoqueMinimoResponseDTO {

    private Long id;

    private String nome;

    private BigDecimal quantidade;

    private String descricao;

    private BigDecimal estoqueMinimo;


}
