package com.estoque_admin.controller;


import com.estoque_admin.dto.MovimentacaoEstoqueRequestDTO;
import com.estoque_admin.dto.MovimentacaoEstoqueResponseDTO;
import com.estoque_admin.service.MovimentacaoEstoqueService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoEstoqueService;

    public MovimentacaoEstoqueController(
            MovimentacaoEstoqueService movimentacaoEstoqueService) {
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
    }

    @PostMapping
    public MovimentacaoEstoqueResponseDTO movimentar(@Valid @RequestBody MovimentacaoEstoqueRequestDTO movimentacaoEstoqueRequestDTO) {
        return movimentacaoEstoqueService.movimentar(movimentacaoEstoqueRequestDTO);


    }

    @GetMapping
    public List<MovimentacaoEstoqueResponseDTO> listar() {
        return movimentacaoEstoqueService.listar();
    }

    @GetMapping("/{id}")
    public MovimentacaoEstoqueResponseDTO buscarPorId(@PathVariable Long id) {
        return movimentacaoEstoqueService.buscarPorId(id);
    }

    @GetMapping("/item/{id}")
    public List<MovimentacaoEstoqueResponseDTO> buscarPorItemEstoque(@PathVariable Long id) {
        return movimentacaoEstoqueService.buscarPorItemEstoque(id);
    }

}