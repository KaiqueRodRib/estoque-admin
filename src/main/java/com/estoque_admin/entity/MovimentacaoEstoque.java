package com.estoque_admin.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "movimentacao_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "A quantidade deve ser maior que zero")
    @NotNull(message = "A quantidade deve ser obrigatório")
    private BigDecimal quantidade;

    @NotNull(message = "O tipo de movimentação deve ser obrigatória")
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    private String observacao;

    @NotNull(message = "O item de estoque deve ser obrigatório")
    @ManyToOne
    @JoinColumn(name = "item_estoque_id")
    private ItemEstoque itemEstoque;
}


