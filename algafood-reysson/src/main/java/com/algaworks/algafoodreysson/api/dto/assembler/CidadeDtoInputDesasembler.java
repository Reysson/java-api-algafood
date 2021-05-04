package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.input.CidadeDtoInput;
import com.algaworks.algafoodreysson.domain.model.Cidade;
import com.algaworks.algafoodreysson.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeDtoInputDesasembler {
    
    @Autowired
    private ModelMapper mapper;
    
    public Cidade toDomainObject(CidadeDtoInput cidadeDtoInput){
        return mapper.map(cidadeDtoInput,Cidade.class);
    }
    
    public void copyToDomainObject(CidadeDtoInput cidadeDtoInput, Cidade cidade){
        cidade.setEstado(new Estado());
        
        mapper.map(cidadeDtoInput, cidade);
    }
}
