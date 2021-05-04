package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.CozinhaDto;
import com.algaworks.algafoodreysson.api.dto.RestauranteDto;
import com.algaworks.algafoodreysson.domain.model.Restaurante;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDtoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    //conversao para um restaurante
    public RestauranteDto toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDto.class);
//        CozinhaDto cozinha = new CozinhaDto();
//        cozinha.setId(restaurante.getCozinha().getId());
//        cozinha.setNome(restaurante.getCozinha().getNome());
//
//        RestauranteDto restauranteDto = new RestauranteDto();
//        restauranteDto.setId(restaurante.getId());
//        restauranteDto.setNome(restaurante.getNome());
//        restauranteDto.setTaxaFrete(restaurante.getTaxaFrete());
//        restauranteDto.setCozinha(cozinha);
//
//        return restauranteDto;
    }

    //conversao para uma lista de restaurantes
    public List<RestauranteDto> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());

    }
}
