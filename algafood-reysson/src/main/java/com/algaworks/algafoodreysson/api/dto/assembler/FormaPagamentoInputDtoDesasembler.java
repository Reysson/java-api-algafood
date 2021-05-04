package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.input.FormaPagamentoInputDto;
import com.algaworks.algafoodreysson.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDtoDesasembler {

    @Autowired
    private ModelMapper mapper;

    public FormaPagamento toDomainObject(FormaPagamentoInputDto formaPagamentoInputDto) {
        return mapper.map(formaPagamentoInputDto, FormaPagamento.class);
    }
    
    public void copyToDomainObject(FormaPagamentoInputDto formaPagamentoInputDto, FormaPagamento formaPagamento){
         mapper.map(formaPagamentoInputDto, formaPagamento);
    }

}
