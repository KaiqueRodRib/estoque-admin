package com.estoque_admin.service;


import com.estoque_admin.exception.EstoqueInsuficienteException;
import com.estoque_admin.repository.ItemEstoqueRepository;
import com.estoque_admin.repository.MovimentacaoEstoqueRepository;
import org.springframework.stereotype.Service;

import com.estoque_admin.entity.ItemEstoque;
import com.estoque_admin.entity.MovimentacaoEstoque;

import java.math.BigDecimal;

import com.estoque_admin.exception.RecursoNaoEncontradoException;

@Service
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private final ItemEstoqueRepository itemEstoqueRepository;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository movimentacaoEstoqueRepository,
                                      ItemEstoqueRepository itemEstoqueRepository) {

        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
        this.itemEstoqueRepository = itemEstoqueRepository;
    }

    public MovimentacaoEstoque movimentar(MovimentacaoEstoque movimentacaoEstoque) {
        ItemEstoque itemEstoque = itemEstoqueRepository
                .findById(movimentacaoEstoque.getItemEstoque().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Item de estoque não encontrado"
                ));


        if (movimentacaoEstoque.getTipo().equalsIgnoreCase("ENTRADA")) {

            BigDecimal novaQuantidade = itemEstoque.getQuantidade()
                    .add(movimentacaoEstoque.getQuantidade());

            itemEstoque.setQuantidade((novaQuantidade));
        } else if (movimentacaoEstoque.getTipo().equalsIgnoreCase("SAIDA")) {

            if (itemEstoque.getQuantidade()
                    .compareTo(movimentacaoEstoque.getQuantidade()) < 0) {

                throw new EstoqueInsuficienteException(
                        "Quantidade insuficente em estoque"
                );
            }

            BigDecimal novaQuantidade = itemEstoque.getQuantidade()
                    .subtract(movimentacaoEstoque.getQuantidade());
            itemEstoque.setQuantidade(novaQuantidade);

        }
        itemEstoqueRepository.save(itemEstoque);
        movimentacaoEstoque.setItemEstoque(itemEstoque);

        return movimentacaoEstoqueRepository.save(movimentacaoEstoque);
    }
}
