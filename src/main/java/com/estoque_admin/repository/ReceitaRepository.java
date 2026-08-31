package com.estoque_admin.repository;

import com.estoque_admin.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita,Long> {
}
