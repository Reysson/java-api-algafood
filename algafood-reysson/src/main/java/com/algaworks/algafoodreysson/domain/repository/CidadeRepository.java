/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.algafoodreysson.domain.repository;

import com.algaworks.algafoodreysson.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reysson
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
    
    
}
