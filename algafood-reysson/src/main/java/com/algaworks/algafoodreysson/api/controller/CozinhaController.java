package com.algaworks.algafoodreysson.api.controller;

import com.algaworks.algafoodreysson.api.dto.CozinhaDto;
import com.algaworks.algafoodreysson.api.dto.assembler.CozinhaDtoAssembler;
import com.algaworks.algafoodreysson.api.dto.assembler.CozinhaDtoInputDesasembler;
import com.algaworks.algafoodreysson.api.dto.input.CozinhaInputDto;
import com.algaworks.algafoodreysson.domain.model.Cozinha;
import com.algaworks.algafoodreysson.domain.repository.CozinhaRepository;
import com.algaworks.algafoodreysson.domain.service.CozinhaService;
import java.util.List;
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

@RestController
//Tipos de retorno que cada metodo pode retornar um JSON ou XML
//@RequestMapping(value = "/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;
    
    @Autowired
    private CozinhaDtoAssembler cozinhaDtoAssembler;
    
    @Autowired
    private CozinhaDtoInputDesasembler cozinhaDtoInputDesassembler;

    @GetMapping
    public List<CozinhaDto> listar() {
        return cozinhaDtoAssembler.toCollectionModel(cozinhaRepository.findAll());
    }

    @GetMapping("/{id}")
    public CozinhaDto buscar(@PathVariable("id") Integer id) {
        return cozinhaDtoAssembler.toModel(cozinhaService.buscarOuFalhar(id));
    }

    @GetMapping("/por-nome")
    public List<Cozinha> buscarPorNome(String nome) {
        return cozinhaRepository.findByNomeContaining(nome);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CozinhaDto salvar(@RequestBody CozinhaInputDto cozinhaInputDto) {
        Cozinha cozinha = cozinhaDtoInputDesassembler.toDomainObject(cozinhaInputDto);
        return cozinhaDtoAssembler.toModel(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public CozinhaDto atualizar(@PathVariable Integer id, @RequestBody CozinhaInputDto cozinhaInputDto) {
        Cozinha cozinhaAtualizada = cozinhaService.buscarOuFalhar(id);
        
        cozinhaDtoInputDesassembler.copyToDomainObject(cozinhaInputDto, cozinhaAtualizada);

        return  cozinhaDtoAssembler.toModel(cozinhaService.salvar(cozinhaAtualizada));

    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Cozinha> deletar(@PathVariable Integer id) {
//
//        try {
//
//            cozinhaService.remover(id);
//            return ResponseEntity.noContent().build();
//
//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.notFound().build();
//        } catch (EntidadeEmUsoException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        cozinhaService.remover(id);
    }
}
