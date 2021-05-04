package com.algaworks.algafoodreysson.api.dto.assembler;

import com.algaworks.algafoodreysson.api.dto.FormaPagamentoDto;
import com.algaworks.algafoodreysson.domain.model.FormaPagamento;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FormaPagamentoDtoAssembler {
        
    @Autowired
    private ModelMapper modelMapper;
    
    public List<FormaPagamentoDto> toCollectionModel(Collection<FormaPagamento> pagamentos){
        return pagamentos.stream()
                .map(pagamento -> toModel(pagamento))
                .collect(Collectors.toList());
    }
    
    public FormaPagamentoDto toModel(FormaPagamento formaPagamento){
        return modelMapper.map(formaPagamento, FormaPagamentoDto.class);
    }
}
