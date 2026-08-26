package com.estoque_admin.dto;

import com.estoque_admin.entity.TipoMovimentacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class MovimentacaoEstoqueResponseDTO {

    private Long id;

    private BigDecimal quantidade;

    private TipoMovimentacao tipo;

    private String observacao;

    private Long itemEstoqueId;
}
