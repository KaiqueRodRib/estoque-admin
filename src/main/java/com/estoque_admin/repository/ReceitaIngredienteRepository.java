package com.estoque_admin.repository;

import com.estoque_admin.entity.ReceitaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, Long> {

    List<ReceitaIngrediente> findByReceitaId(Long receitaId);


}
