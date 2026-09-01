package com.estoque_admin.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReceitaIngredienteRequestDTO {

    @NotNull
    private Long itemEstoqueId;

    @NotNull
    @Positive
    private BigDecimal quantidade;
}
