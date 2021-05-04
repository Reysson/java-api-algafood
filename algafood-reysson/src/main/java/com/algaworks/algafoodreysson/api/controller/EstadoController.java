package com.algaworks.algafoodreysson.api.controller;

import com.algaworks.algafoodreysson.api.dto.EstadoDto;
import com.algaworks.algafoodreysson.api.dto.assembler.EstadoDtoAssembler;
import com.algaworks.algafoodreysson.api.dto.assembler.EstadoDtoInputDesasembler;
import com.algaworks.algafoodreysson.api.dto.input.EstadoInputDto;
import com.algaworks.algafoodreysson.domain.model.Estado;
import com.algaworks.algafoodreysson.domain.repository.EstadoRepository;
import com.algaworks.algafoodreysson.domain.service.EstadoService;
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
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired
    private EstadoDtoAssembler estadoDtoAssembler;
    
    @Autowired
    private EstadoDtoInputDesasembler estadoDtoInputDesasembler;

    @Autowired
    private EstadoService estadoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<EstadoDto> listar() {
        return estadoDtoAssembler.toCollectionModel(estadoRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public EstadoDto buscar(@PathVariable Integer id){
        return estadoDtoAssembler.toModel(estadoService.buscarOuFalhar(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EstadoDto adicionar(@RequestBody @Valid EstadoInputDto estadoInputDto) {
        Estado estado = estadoDtoInputDesasembler.toDomainObject(estadoInputDto);
        return estadoDtoAssembler.toModel(estadoService.salvar(estado));

    }

    @PutMapping("/{id}")
    public EstadoDto atualizar(@PathVariable Integer id, @RequestBody @Valid EstadoInputDto estadoInputDto) {
        
        Estado estadoAtualizado = estadoService.buscarOuFalhar(id);

        estadoDtoInputDesasembler.copyToDomainObject(estadoInputDto, estadoAtualizado);

        return estadoDtoAssembler.toModel(estadoService.salvar(estadoAtualizado));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer id) {
        estadoService.deletar(id);
    }

}
