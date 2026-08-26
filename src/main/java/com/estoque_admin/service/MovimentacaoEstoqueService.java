package com.estoque_admin.service;


import com.estoque_admin.dto.MovimentacaoEstoqueRequestDTO;
import com.estoque_admin.dto.MovimentacaoEstoqueResponseDTO;
import com.estoque_admin.entity.TipoMovimentacao;
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

    public MovimentacaoEstoqueResponseDTO movimentar(MovimentacaoEstoqueRequestDTO movimentacaoEstoqueRequestDTO) {
        ItemEstoque itemEstoque = itemEstoqueRepository
                .findById(movimentacaoEstoqueRequestDTO.getItemEstoqueId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Item de estoque não encontrado"
                ));

        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();

        movimentacaoEstoque.setQuantidade(
                movimentacaoEstoqueRequestDTO.getQuantidade()
        );

        movimentacaoEstoque.setTipo(
                movimentacaoEstoqueRequestDTO.getTipo()
        );

        movimentacaoEstoque.setObservacao(
                movimentacaoEstoqueRequestDTO.getObservacao()
        );

        movimentacaoEstoque.setItemEstoque(itemEstoque);


        if (movimentacaoEstoque.getTipo() == TipoMovimentacao.ENTRADA) {

            BigDecimal novaQuantidade = itemEstoque.getQuantidade()
                    .add(movimentacaoEstoque.getQuantidade());

            itemEstoque.setQuantidade((novaQuantidade));
        } else if (movimentacaoEstoque.getTipo() == TipoMovimentacao.SAIDA) {

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

        MovimentacaoEstoque movimentacaoSalva =
                movimentacaoEstoqueRepository.save(movimentacaoEstoque);

        MovimentacaoEstoqueResponseDTO responseDTO =
                new MovimentacaoEstoqueResponseDTO();

        responseDTO.setId(movimentacaoSalva.getId());
        responseDTO.setQuantidade(movimentacaoSalva.getQuantidade());
        responseDTO.setTipo(movimentacaoSalva.getTipo());
        responseDTO.setObservacao(movimentacaoSalva.getObservacao());
        responseDTO.setItemEstoqueId(movimentacaoSalva.getItemEstoque().getId());

        return responseDTO;
    }
}
