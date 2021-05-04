/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.algafoodreysson.api.controller;

import com.algaworks.algafoodreysson.api.dto.CidadeDto;
import com.algaworks.algafoodreysson.api.dto.assembler.CidadeDtoAssembler;
import com.algaworks.algafoodreysson.api.dto.assembler.CidadeDtoInputDesasembler;
import com.algaworks.algafoodreysson.api.dto.input.CidadeDtoInput;
import com.algaworks.algafoodreysson.domain.exceptions.EstadoNaoEncontradoException;
import com.algaworks.algafoodreysson.domain.exceptions.NegocioException;
import com.algaworks.algafoodreysson.domain.model.Cidade;
import com.algaworks.algafoodreysson.domain.repository.CidadeRepository;
import com.algaworks.algafoodreysson.domain.service.CidadeService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
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

/**
 *
 * @author reysson
 */
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;
    
    @Autowired
    private CidadeDtoAssembler cidadeDtoAssembler;
    
    
    @Autowired
    private CidadeDtoInputDesasembler cidadeDtoInputDesasembler;

    @GetMapping
    public List<CidadeDto> listar() {
        return cidadeDtoAssembler.toCollectionModel(cidadeRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDto adicionar(@RequestBody @Valid CidadeDtoInput cidadeInput) {
        try{
            Cidade cidade = cidadeDtoInputDesasembler.toDomainObject(cidadeInput);
            return cidadeDtoAssembler.toModel(cidadeService.salvar(cidade));
        }catch(EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Integer id, @RequestBody @Valid CidadeDtoInput cidadeDtoInput) {
        
        try{
            Cidade cidadeAtualizada = cidadeService.buscarOuFalhar(id);
        
            cidadeDtoInputDesasembler.copyToDomainObject(cidadeDtoInput, cidadeAtualizada);

            return cidadeService.salvar(cidadeAtualizada);
        }catch(EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage());
        }


    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer id) {

        cidadeService.deletar(id);

    }
}
