package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.input.CozinhaInputDto;
import com.algaworks.algafoodreysson.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDtoInputDesasembler {

    @Autowired
    private ModelMapper mapper;

    public Cozinha toDomainObject(CozinhaInputDto cozinhaInputDto) {
        return mapper.map(cozinhaInputDto, Cozinha.class);

    }

    public void copyToDomainObject(CozinhaInputDto cozinhaInputDto, Cozinha cozinha) {
        mapper.map(cozinhaInputDto, cozinha);

    }

}
