package com.estoque_admin.controller;

import com.estoque_admin.dto.ReceitaRequestDTO;
import com.estoque_admin.entity.Receita;
import com.estoque_admin.service.ReceitaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController (ReceitaService receitaService){

        this.receitaService = receitaService;
    }

    @PostMapping
    public Receita criarReceita(@RequestBody ReceitaRequestDTO receitaRequestDTO){
        return receitaService.salvar(receitaRequestDTO);
    }

    @GetMapping
    public List<Receita> listarTodos(){
        return receitaService.listarTodos();
    }

}
