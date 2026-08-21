package com.estoque_admin.service;

import com.estoque_admin.entity.Categoria;
import com.estoque_admin.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria criar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public Categoria atualizar(Long id, Categoria categoria){
        Categoria categoriaExistente = buscarPorId(id);

        categoriaExistente.setName(categoria.getName());

        return categoriaRepository.save(categoriaExistente);
    }

    public void excluir(Long id){
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }




}