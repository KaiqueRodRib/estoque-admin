package com.estoque_admin.repository;

import com.estoque_admin.entity.ItemEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Long> {

    @Query("SELECT i FROM ItemEstoque i WHERE i.quantidade <= i.estoqueMinimo")
    List<ItemEstoque> buscarItensAbaixoDoMinimo();


}
