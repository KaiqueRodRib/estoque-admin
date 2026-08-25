package com.estoque_admin.entity;


import jakarta.persistence.*;
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

    private BigDecimal quantidade;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "item_estoque_id")
    private ItemEstoque itemEstoque;
}
