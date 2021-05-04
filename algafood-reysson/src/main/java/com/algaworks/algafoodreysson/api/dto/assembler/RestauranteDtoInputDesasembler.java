/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.input.RestauranteInputDto;
import com.algaworks.algafoodreysson.domain.model.Cidade;
import com.algaworks.algafoodreysson.domain.model.Cozinha;
import com.algaworks.algafoodreysson.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDtoInputDesasembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Restaurante toDomainObject(RestauranteInputDto restauranteInputDto) {
        
//        Cozinha cozinha = new Cozinha();
//        cozinha.setId(restauranteInputDto.getIdCozinha().getId());
//        
//        Restaurante restaurante = new Restaurante();
//        restaurante.setNome(restauranteInputDto.getNome());
//        restaurante.setTaxaFrete(restauranteInputDto.getTaxaFrete());
//        restaurante.setCozinha(cozinha);
        
        
        return modelMapper.map(restauranteInputDto, Restaurante.class);
    }
    
    
    public void copyToDomainObject(RestauranteInputDto restauranteInputDto, Restaurante restaurante){
        //Para evitar exceção\
        restaurante.setCozinha(new Cozinha());
        
        if(restaurante.getEndereco() != null){
            //Para evitar exceção\
            restaurante.getEndereco().setCidade(new Cidade());
        }
        
        modelMapper.map(restauranteInputDto, restaurante);
    }
}
