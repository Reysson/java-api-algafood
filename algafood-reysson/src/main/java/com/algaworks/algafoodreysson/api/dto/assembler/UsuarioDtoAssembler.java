package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.UsuarioDto;
import com.algaworks.algafoodreysson.domain.model.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoAssembler {
    
    @Autowired
    private ModelMapper mapper;
    
    public UsuarioDto toModel(Usuario usuario){
        return mapper.map(usuario, UsuarioDto.class);
    }
    
    public List<UsuarioDto> toCollectionModel(List<Usuario> usuarios){
        return usuarios.stream()
                .map(ref -> toModel(ref))
                .collect(Collectors.toList());
    }
}
