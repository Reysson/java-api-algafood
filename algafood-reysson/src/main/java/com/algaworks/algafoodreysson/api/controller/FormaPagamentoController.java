package com.algaworks.algafoodreysson.api.controller;

import com.algaworks.algafoodreysson.api.dto.FormaPagamentoDto;
import com.algaworks.algafoodreysson.api.dto.assembler.FormaPagamentoDtoAssembler;
import com.algaworks.algafoodreysson.api.dto.assembler.FormaPagamentoInputDtoDesasembler;
import com.algaworks.algafoodreysson.api.dto.input.FormaPagamentoInputDto;
import com.algaworks.algafoodreysson.domain.model.FormaPagamento;
import com.algaworks.algafoodreysson.domain.service.FormaPagamentoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formas-pagamentos")
public class FormaPagamentoController {
    
    @Autowired
    private FormaPagamentoService formaPagamentoService;
    
    @Autowired
    private FormaPagamentoDtoAssembler pagamentoAssembler;
    
    @Autowired
    private FormaPagamentoInputDtoDesasembler pagamentoinputDesasembler;
    
    @ResponseStatus(HttpStatus.OK)//200
    @GetMapping
    public List<FormaPagamentoDto> listar(){
        return pagamentoAssembler.toCollectionModel(formaPagamentoService.listar());
    }
    
    @ResponseStatus(HttpStatus.CREATED)// 201
    @PostMapping
    public FormaPagamentoDto salvar(@RequestBody @Valid FormaPagamentoInputDto formaPagamentoInput){
        FormaPagamento pagamento = pagamentoinputDesasembler.toDomainObject(formaPagamentoInput);
        return pagamentoAssembler.toModel(formaPagamentoService.salvar(pagamento));
    }
    
    @ResponseStatus(HttpStatus.OK)// 200
    @GetMapping("/{id}")
    public FormaPagamentoDto buscar(@PathVariable Integer id){
        return pagamentoAssembler.toModel(formaPagamentoService.buscarOuFalhar(id));
    }
    
    @ResponseStatus(HttpStatus.OK)//200
    @PutMapping("/{id}")
    public FormaPagamentoDto atualizar(@PathVariable Integer id, 
            @RequestBody @Valid FormaPagamentoInputDto pagamentoInputDto){
        
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(id);
        
        pagamentoinputDesasembler.copyToDomainObject(pagamentoInputDto, formaPagamento);
        
        return pagamentoAssembler.toModel(formaPagamentoService.salvar(formaPagamento));
        
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)//204
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        formaPagamentoService.deletar(id);
    }
    
}
