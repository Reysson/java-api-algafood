package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.CozinhaDto;
import com.algaworks.algafoodreysson.domain.model.Cozinha;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDtoAssembler {
    
    @Autowired
    private ModelMapper mapper;
    
    
    
    public CozinhaDto toModel(Cozinha cozinha){
        return mapper.map(cozinha, CozinhaDto.class);
    }
    
    public List<CozinhaDto> toCollectionModel(List<Cozinha> cozinha){ 
           return cozinha.stream()
                   .map(cozinhas -> toModel(cozinhas))
                   .collect(Collectors.toList());
    }
}
