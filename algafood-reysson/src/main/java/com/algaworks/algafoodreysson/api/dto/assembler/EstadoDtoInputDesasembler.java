package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.input.EstadoInputDto;
import com.algaworks.algafoodreysson.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoDtoInputDesasembler {

    @Autowired
    private ModelMapper mapper;

    public Estado toDomainObject(EstadoInputDto estadoInputDto) {
        return mapper.map(estadoInputDto, Estado.class);
    }

    public void copyToDomainObject(EstadoInputDto estadoInputDto, Estado estado) {

        mapper.map(estadoInputDto, estado);
    }
}
