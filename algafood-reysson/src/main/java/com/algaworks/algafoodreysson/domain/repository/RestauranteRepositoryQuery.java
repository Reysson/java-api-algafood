/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.algafoodreysson.domain.repository;

import com.algaworks.algafoodreysson.domain.model.Restaurante;
import java.util.List;

/**
 *
 * @author reysson
 */
public interface RestauranteRepositoryQuery {

    List<Restaurante> find(String nome, Float taxaFreteInicial, Float taxaFreteFinal);
    
    List<Restaurante> buscarRestaurantePorCozinha(String nomeCozinha);
    
}
