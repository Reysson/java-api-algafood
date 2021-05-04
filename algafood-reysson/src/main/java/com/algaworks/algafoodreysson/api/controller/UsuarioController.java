package com.algaworks.algafoodreysson.api.controller;

import com.algaworks.algafoodreysson.api.dto.UsuarioDto;
import com.algaworks.algafoodreysson.api.dto.assembler.UsuarioDtoAssembler;
import com.algaworks.algafoodreysson.api.dto.assembler.UsuarioInputDtoDesassembler;
import com.algaworks.algafoodreysson.api.dto.input.SenhaInputDto;
import com.algaworks.algafoodreysson.api.dto.input.UsuarioInpuDto;
import com.algaworks.algafoodreysson.domain.model.Usuario;
import com.algaworks.algafoodreysson.domain.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDtoAssembler usuarioAssembler;

    @Autowired
    private UsuarioInputDtoDesassembler usuarioDesasembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UsuarioDto> listar() {
        return usuarioAssembler.toCollectionModel(usuarioService.listar());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UsuarioDto buscar(@PathVariable Integer id) {
        return usuarioAssembler.toModel(usuarioService.buscarOuFalhar(id));
    }

    @ResponseStatus(HttpStatus.CREATED) //201
    @PostMapping
    public UsuarioDto salvar(@RequestBody @Valid UsuarioInpuDto usuarioInpuDto) {
        Usuario usuario = usuarioDesasembler.toDomainObject(usuarioInpuDto);
        return usuarioAssembler.toModel(usuarioService.salvar(usuario));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public UsuarioDto atualizar(@PathVariable Integer id, @RequestBody @Valid UsuarioInpuDto usuarioInpuDto) {

        Usuario usuarioAtual = usuarioService.buscarOuFalhar(id);

        usuarioDesasembler.copyPropertiets(usuarioInpuDto, usuarioAtual);

        return usuarioAssembler.toModel(usuarioService.salvar(usuarioAtual));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{id}/senha")
    public void atualizarSenha(@PathVariable Integer id, @RequestBody @Valid SenhaInputDto senhaInputDto) {
        usuarioService.alterarSenha(id, senhaInputDto.getSenhaAtual(), senhaInputDto.getNovaSenha());
    }
}
