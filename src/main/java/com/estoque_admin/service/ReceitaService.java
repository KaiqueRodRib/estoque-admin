package com.estoque_admin.service;

import com.estoque_admin.dto.ReceitaIngredienteRequestDTO;
import com.estoque_admin.dto.ReceitaRequestDTO;
import com.estoque_admin.entity.ItemEstoque;
import com.estoque_admin.entity.Receita;
import com.estoque_admin.entity.ReceitaIngrediente;
import com.estoque_admin.exception.RecursoNaoEncontradoException;
import com.estoque_admin.repository.ItemEstoqueRepository;
import com.estoque_admin.repository.ReceitaIngredienteRepository;
import com.estoque_admin.repository.ReceitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    private final ReceitaIngredienteRepository receitaIngredienteRepository;

    private final ItemEstoqueRepository itemEstoqueRepository;


    public ReceitaService(
            ReceitaRepository receitaRepository,
            ReceitaIngredienteRepository receitaIngredienteRepository,
            ItemEstoqueRepository itemEstoqueRepository) {

        this.receitaRepository = receitaRepository;
        this.receitaIngredienteRepository = receitaIngredienteRepository;
        this.itemEstoqueRepository = itemEstoqueRepository;
    }

    @Transactional
    public Receita salvar(ReceitaRequestDTO receitaRequestDTO) {

        Receita receita = new Receita();

        receita.setNome(receitaRequestDTO.getNome());
        receita.setDataCadastro(LocalDateTime.now());

        receita = receitaRepository.save(receita);

        for (ReceitaIngredienteRequestDTO ingrediente : receitaRequestDTO.getIngredientes()) {

            ItemEstoque itemEstoque = itemEstoqueRepository
                    .findById(ingrediente.getItemEstoqueId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Item não encontrado"
            ));

            ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();

            receitaIngrediente.setReceita(receita);
            receitaIngrediente.setItemEstoque(itemEstoque);
            receitaIngrediente.setQuantidade(ingrediente.getQuantidade());

            receitaIngredienteRepository.save(receitaIngrediente);
        }

        return receita;
    }

    public List<Receita> listarTodos(){
        return receitaRepository.findAll();
    }

    public List<ReceitaIngrediente> buscaIngredienteReceita(Long receitaId){

        receitaRepository
                .findById(receitaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Receita não encontrada"
                ));

        return receitaIngredienteRepository.findByReceitaId(receitaId);
    }

}
