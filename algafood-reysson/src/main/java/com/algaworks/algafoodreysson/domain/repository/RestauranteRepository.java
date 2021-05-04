/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.algafoodreysson.domain.repository;

import com.algaworks.algafoodreysson.domain.model.Restaurante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reysson
 */
@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer>, 
        RestauranteRepositoryQuery, JpaSpecificationExecutor<Restaurante>{
    
    @Query("from Restaurante r left join r.cozinha")
    List<Restaurante> findAll();
    
    List<Restaurante> findByTaxaFreteBetween(Float taxaInicial, Float taxaFinal);
    
    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Integer id);
    
    //outra forma, a query agora esta no META-INF
   // @Query("from Restaurante where nome like %:nome% and cozinha.id = :cozinha")
    List<Restaurante> consultaPorNome(String nome, @Param("cozinha") Integer id);
    
    /*Esse metodo esta implementado em infraestutura. O Spring tem a inteligencia
    de associar ple assinatura do método a implementacao nessa interface*/
    List<Restaurante> find(String nome, Float taxaFreteInicial, Float taxaFreteFinal);
    
    
    List<Restaurante> buscarRestaurantePorCozinha(String nomeCozinha);
    
}
