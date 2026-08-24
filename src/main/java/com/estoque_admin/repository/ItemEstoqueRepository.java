package com.estoque_admin.repository;

import com.estoque_admin.entity.ItemEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Long> {
}
