package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.CidadeDto;
import com.algaworks.algafoodreysson.domain.model.Cidade;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeDtoAssembler {
    
    @Autowired
    private ModelMapper mapper;
    
    public CidadeDto toModel(Cidade cidade){
        return mapper.map(cidade, CidadeDto.class);
    }
    
    public List<CidadeDto> toCollectionModel(List<Cidade> cidades){
        return cidades.stream()
                .map(ref -> toModel(ref))
                .collect(Collectors.toList());
    }
}
