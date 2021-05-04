package com.algaworks.algafoodreysson.domain.service;

import com.algaworks.algafoodreysson.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodreysson.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodreysson.domain.exceptions.GrupoNaoEncontradoExeception;
import com.algaworks.algafoodreysson.domain.model.Grupo;
import com.algaworks.algafoodreysson.domain.repository.GrupoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoService {
    
    @Autowired
    private GrupoRepository grupoRepository;
    
    private static final String MSG_GRUPO_NAO_ENCONTRADO = "Não existe cadastro"
            + "de grupo com código %d";
    
    private static final String MSG_GRUPO_EM_USO = "O grupo de código %d não pode"
            + "ser removido pois está em uso";
    
    @Transactional
    public List<Grupo> listar(){
        return grupoRepository.findAll();
    }
    
    @Transactional
    public Grupo salvar(Grupo grupo){
        return grupoRepository.save(grupo);
    }
    
    @Transactional
    public void deletar(Integer id){
        try{
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        }catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format(MSG_GRUPO_NAO_ENCONTRADO, id));
            
        }catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
        }
    }
    
    @Transactional
    public Grupo buscarOuFalhar(Integer id){
        return grupoRepository.findById(id).orElseThrow(
        () -> new GrupoNaoEncontradoExeception(String.format(MSG_GRUPO_NAO_ENCONTRADO, id)));
    }
}
