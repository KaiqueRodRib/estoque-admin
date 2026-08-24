package com.estoque_admin.service;


import com.estoque_admin.repository.ItemEstoqueRepository;
import com.estoque_admin.repository.MovimentacaoEstoqueRepository;
import org.springframework.stereotype.Service;

import com.estoque_admin.entity.ItemEstoque;
import com.estoque_admin.entity.MovimentacaoEstoque;

import java.math.BigDecimal;

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
                .orElse(null);

        if (itemEstoque == null) {
            return null;
        }

        if (movimentacaoEstoque.getTipo().equalsIgnoreCase("ENTRADA")) {

            BigDecimal novaQuantidade = itemEstoque.getQuantidade()
                    .add(movimentacaoEstoque.getQuantidade());

            itemEstoque.setQuantidade((novaQuantidade));
        } else if (movimentacaoEstoque.getTipo().equalsIgnoreCase("SAIDA")) {

            if (itemEstoque.getQuantidade()
                    .compareTo(movimentacaoEstoque.getQuantidade()) < 0) {

                return null;
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
