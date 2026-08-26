package com.estoque_admin.controller;


import com.estoque_admin.entity.MovimentacaoEstoque;
import com.estoque_admin.service.MovimentacaoEstoqueService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoEstoqueService;

    public MovimentacaoEstoqueController(
            MovimentacaoEstoqueService movimentacaoEstoqueService) {
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
    }

    @PostMapping
    public MovimentacaoEstoque movimentar(@Valid @RequestBody MovimentacaoEstoque movimentacaoEstoque) {
        return movimentacaoEstoqueService.movimentar(movimentacaoEstoque);
    }
}