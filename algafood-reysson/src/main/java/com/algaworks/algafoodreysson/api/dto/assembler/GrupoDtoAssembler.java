package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.GrupoDto;
import com.algaworks.algafoodreysson.domain.model.Grupo;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoDtoAssembler {
    
    @Autowired
    private ModelMapper mapper;
    
    public GrupoDto toModel (Grupo grupo){
        return mapper.map(grupo, GrupoDto.class);
    }
    
    public List<GrupoDto> toCollectionModel(List<Grupo> grupos){
        return grupos.stream()
                .map(ref -> toModel(ref))
                .collect(Collectors.toList());
    }
}
