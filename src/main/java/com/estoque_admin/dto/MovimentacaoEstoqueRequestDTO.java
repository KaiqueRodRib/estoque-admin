package com.estoque_admin.dto;

import com.estoque_admin.entity.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class MovimentacaoEstoqueRequestDTO {

    @NotNull
    @Positive
    private BigDecimal quantidade;

    @NotNull
    private TipoMovimentacao tipo;

    private String observacao;

    @NotNull
    private Long itemEstoqueId;

}
