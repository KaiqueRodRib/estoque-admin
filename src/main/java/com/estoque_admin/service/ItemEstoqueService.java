package com.estoque_admin.service;


import com.estoque_admin.entity.ItemEstoque;
import com.estoque_admin.repository.ItemEstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemEstoqueService {

    private final ItemEstoqueRepository itemEstoqueRepository;

    public ItemEstoqueService(ItemEstoqueRepository itemEstoqueRepository) {
        this.itemEstoqueRepository = itemEstoqueRepository;
    }

    public ItemEstoque salvar(ItemEstoque itemEstoque) {
        return itemEstoqueRepository.save(itemEstoque);
    }

    public List<ItemEstoque> listarTodos() {
        return itemEstoqueRepository.findAll();
    }

    public ItemEstoque buscarPorId(Long id) {
        return itemEstoqueRepository.findById(id)
                .orElse(null);
    }

    public ItemEstoque atualizar(Long id, ItemEstoque itemEstoque) {
        ItemEstoque itemExistente = itemEstoqueRepository.findById(id)
                .orElse(null);
        if (itemExistente == null) {
            return null;
        }
        itemExistente.setNome(itemEstoque.getNome());
        itemExistente.setDescricao(itemEstoque.getDescricao());
        itemExistente.setUnidadeMedida(itemEstoque.getUnidadeMedida());
        itemExistente.setQuantidade(itemEstoque.getQuantidade());
        itemExistente.setEstoqueMinimo(itemEstoque.getEstoqueMinimo());
        itemExistente.setCustoUnitario(itemEstoque.getCustoUnitario());
        itemExistente.setCategoria(itemEstoque.getCategoria());
        itemExistente.setFornecedor(itemEstoque.getFornecedor());

        return itemEstoqueRepository.save(itemExistente);

    }

    public void deletar(Long id){
        itemEstoqueRepository.deleteById(id);
    }

    public List<ItemEstoque> buscarItensAbaixoDoMinimo(){

        return itemEstoqueRepository.buscarItensAbaixoDoMinimo();

    }


}
