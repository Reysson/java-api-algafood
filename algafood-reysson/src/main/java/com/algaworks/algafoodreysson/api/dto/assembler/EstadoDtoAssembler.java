package com.algaworks.algafoodreysson.api.dto.assembler;

//montar um um objeto estado em estadoDto

import com.algaworks.algafoodreysson.api.dto.EstadoDto;
import com.algaworks.algafoodreysson.domain.model.Estado;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoDtoAssembler {
    
    @Autowired
    private ModelMapper mapper;
    
    public EstadoDto toModel(Estado estado){
        return mapper.map(estado, EstadoDto.class);
    }
    
    public List<EstadoDto> toCollectionModel(List<Estado> estado){
        return estado.stream()
                .map(estados -> toModel(estados))
                .collect(Collectors.toList());
    
    }
}
