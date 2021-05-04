package com.algaworks.algafoodreysson.domain.repository;

import com.algaworks.algafoodreysson.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer>{
}
