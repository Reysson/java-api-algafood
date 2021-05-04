package com.algaworks.algafoodreysson.api.controller;

import com.algaworks.algafoodreysson.api.dto.GrupoDto;
import com.algaworks.algafoodreysson.api.dto.assembler.GrupoDtoAssembler;
import com.algaworks.algafoodreysson.api.dto.assembler.GrupoInputDtoDesasembler;
import com.algaworks.algafoodreysson.api.dto.input.GrupoInputDto;
import com.algaworks.algafoodreysson.domain.model.Grupo;
import com.algaworks.algafoodreysson.domain.service.GrupoService;
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
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoDtoAssembler grupoAssembler;

    @Autowired
    private GrupoInputDtoDesasembler grupoDesasembler;

    @ResponseStatus(HttpStatus.OK) //200 
    @GetMapping
    public List<GrupoDto> listar() {
        return grupoAssembler.toCollectionModel(grupoService.listar());
    }

    @ResponseStatus(HttpStatus.OK) //200
    @GetMapping("/{id}")
    public GrupoDto buscarPorId(@PathVariable Integer id) {
        return grupoAssembler.toModel(grupoService.buscarOuFalhar(id));
    }

    @ResponseStatus(HttpStatus.OK) //200
    @PutMapping("/{id}")
    public GrupoDto atualizar(@PathVariable Integer id, @RequestBody @Valid GrupoInputDto grupoInputDto) {
        Grupo grupoBuscado = grupoService.buscarOuFalhar(id);

        grupoDesasembler.copyToDomainObject(grupoInputDto, grupoBuscado);

        return grupoAssembler.toModel(grupoService.salvar(grupoBuscado));
    }

    @ResponseStatus(HttpStatus.CREATED) //201
    @PostMapping
    public GrupoDto salvar(@RequestBody @Valid GrupoInputDto grupoInputDto) {
        Grupo grupo = grupoDesasembler.toDomainObject(grupoInputDto);
        return grupoAssembler.toModel(grupoService.salvar(grupo));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        grupoService.deletar(id);
    }
}
