package com.algaworks.algafoodreysson.infraestrutura.repository.spec;

import com.algaworks.algafoodreysson.domain.model.Restaurante;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante>{
    
    
    private String nome;

    public RestauranteComNomeSemelhanteSpec(String nome) {
        this.nome = nome;
    }
    
    @Override
    public Predicate toPredicate(Root<Restaurante> root,
            CriteriaQuery<?> criteria, CriteriaBuilder builder) {
        
        return builder.like(root.get("nome"), "%" + nome + "%");
    }
    
    
}
