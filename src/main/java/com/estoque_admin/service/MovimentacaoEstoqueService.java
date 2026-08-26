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
import java.util.List;

import com.estoque_admin.exception.RecursoNaoEncontradoException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private final ItemEstoqueRepository itemEstoqueRepository;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository movimentacaoEstoqueRepository,
                                      ItemEstoqueRepository itemEstoqueRepository) {

        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
        this.itemEstoqueRepository = itemEstoqueRepository;
    }

    @Transactional
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

    public List<MovimentacaoEstoqueResponseDTO> listar() {

        List<MovimentacaoEstoque> movimentacoes =
                movimentacaoEstoqueRepository.findAll();

        return movimentacoes.stream()
                .map(movimentacao -> {

                    MovimentacaoEstoqueResponseDTO responseDTO =
                            new MovimentacaoEstoqueResponseDTO();

                    responseDTO.setId(movimentacao.getId());
                    responseDTO.setQuantidade(movimentacao.getQuantidade());
                    responseDTO.setTipo(movimentacao.getTipo());
                    responseDTO.setObservacao(movimentacao.getObservacao());
                    responseDTO.setItemEstoqueId(movimentacao.getItemEstoque().getId());

                    return responseDTO;

                })
                .toList();
    }

    public MovimentacaoEstoqueResponseDTO buscarPorId(Long id) {
        MovimentacaoEstoque movimentacaoEstoque = movimentacaoEstoqueRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Movimentação não encontrada"
                ));

        MovimentacaoEstoqueResponseDTO responseDTO =
                new MovimentacaoEstoqueResponseDTO();

        responseDTO.setId(movimentacaoEstoque.getId());
        responseDTO.setQuantidade(movimentacaoEstoque.getQuantidade());
        responseDTO.setTipo(movimentacaoEstoque.getTipo());
        responseDTO.setObservacao(movimentacaoEstoque.getObservacao());
        responseDTO.setItemEstoqueId(movimentacaoEstoque.getItemEstoque().getId());

        return responseDTO;
    }

    public List<MovimentacaoEstoqueResponseDTO> buscarPorItemEstoque(Long itemEstoqueId) {
        List<MovimentacaoEstoque> movimentacoes =
                movimentacaoEstoqueRepository
                        .findByItemEstoqueId(itemEstoqueId);

        return movimentacoes.stream()
                .map(movimentacao -> {

                    MovimentacaoEstoqueResponseDTO responseDTO =
                            new MovimentacaoEstoqueResponseDTO();

                    responseDTO.setId(movimentacao.getId());
                    responseDTO.setQuantidade(movimentacao.getQuantidade());
                    responseDTO.setTipo(movimentacao.getTipo());
                    responseDTO.setObservacao(movimentacao.getObservacao());
                    responseDTO.setItemEstoqueId(movimentacao.getItemEstoque().getId());

                    return responseDTO;

                })
                .toList();
    }
}
