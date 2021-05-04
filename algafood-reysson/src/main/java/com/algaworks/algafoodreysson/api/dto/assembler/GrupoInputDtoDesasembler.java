package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.input.GrupoInputDto;
import com.algaworks.algafoodreysson.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDtoDesasembler {
    
    @Autowired
    private ModelMapper mapper;
    
    public Grupo toDomainObject(GrupoInputDto grupoInputDto){
        return mapper.map(grupoInputDto, Grupo.class);
    }
    
    public void copyToDomainObject(GrupoInputDto grupoInputDto, Grupo grupo){
        mapper.map(grupoInputDto, grupo);
    }
}
