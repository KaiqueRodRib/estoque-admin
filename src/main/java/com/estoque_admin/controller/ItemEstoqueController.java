package com.estoque_admin.controller;


import com.estoque_admin.entity.ItemEstoque;
import com.estoque_admin.service.ItemEstoqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-estoque")
public class ItemEstoqueController {

    private final ItemEstoqueService itemEstoqueService;

    public ItemEstoqueController(ItemEstoqueService itemEstoqueService) {
        this.itemEstoqueService = itemEstoqueService;
    }

    @PostMapping
    public ItemEstoque salvar(@RequestBody ItemEstoque itemEstoque) {
        return itemEstoqueService.salvar(itemEstoque);
    }

    @GetMapping
    public List<ItemEstoque> listarTodos() {
        return itemEstoqueService.listarTodos();
    }

    @GetMapping("/itensminimo")
    public List<ItemEstoque> buscarItensAbaixoDoMinimo() {
        return itemEstoqueService.buscarItensAbaixoDoMinimo();
    }

    @GetMapping("/{id}")
    public ItemEstoque buscarPorId(@PathVariable Long id) {
        return itemEstoqueService.buscarPorId(id);
    }


    @PutMapping("/{id}")
    public ItemEstoque atualizar(@PathVariable Long id,
                                 @RequestBody ItemEstoque itemEstoque) {
        return itemEstoqueService.atualizar(id, itemEstoque);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        itemEstoqueService.deletar(id);
    }

}
