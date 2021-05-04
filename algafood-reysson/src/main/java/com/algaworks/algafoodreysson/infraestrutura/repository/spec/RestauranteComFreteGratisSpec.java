/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.algafoodreysson.infraestrutura.repository.spec;

import com.algaworks.algafoodreysson.domain.model.Restaurante;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author reysson
 */
public class RestauranteComFreteGratisSpec implements Specification<Restaurante>{

    @Override
    public Predicate toPredicate(Root<Restaurante> root,
            CriteriaQuery<?> criteria, CriteriaBuilder builder) {
        
        return builder.equal(root.get("taxaFrete"), 0.00);
    }
    
}
