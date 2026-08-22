package com.estoque_admin.service;

import java.util.List;

import com.estoque_admin.entity.Fornecedor;
import com.estoque_admin.repository.FornecedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return fornecedorRepository.findById(id)
                .orElse(null);

    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedor) {
        Fornecedor fornecedorExistente = fornecedorRepository.findById(id)
                .orElse(null);


        if (fornecedorExistente == null) {
            return null;
        }

        fornecedorExistente.setNome(fornecedor.getNome());
        fornecedorExistente.setCnpj(fornecedor.getCnpj());
        fornecedorExistente.setEmail(fornecedor.getEmail());
        fornecedorExistente.setTelefone(fornecedor.getTelefone());

        return fornecedorRepository.save(fornecedorExistente);
    }

    public void deletar(Long id){
        fornecedorRepository.deleteById(id);

    }

}
