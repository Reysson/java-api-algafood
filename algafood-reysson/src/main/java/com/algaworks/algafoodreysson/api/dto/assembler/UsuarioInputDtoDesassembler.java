package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.input.UsuarioInpuDto;
import com.algaworks.algafoodreysson.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDtoDesassembler {
    
    @Autowired
    private ModelMapper mapper;
    
    public Usuario toDomainObject(UsuarioInpuDto usuarioInpuDto){
        return mapper.map(usuarioInpuDto, Usuario.class);
    }
    
    public void copyPropertiets(UsuarioInpuDto usuarioInpuDto,Usuario usuario){
        mapper.map(usuarioInpuDto, usuario);
    }
}
