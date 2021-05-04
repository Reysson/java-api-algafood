/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.algafoodreysson.domain.service;

import com.algaworks.algafoodreysson.domain.exceptions.CozinhaNaoEncontradaException;
import com.algaworks.algafoodreysson.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodreysson.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodreysson.domain.model.Cozinha;
import com.algaworks.algafoodreysson.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CozinhaService {

    private static final String MSG_COZINHA_NAO_ENCONTRADA
            = "Não existe um cadastro de cozinha com o código %d";
    private static final String MSG_COZINHA_EM_USO
            = "Cozinha de código %d não pode ser removido pois está em uso";

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void remover(Integer id) {
        try {

            cozinhaRepository.deleteById(id);
            cozinhaRepository.flush();

      
        } catch (DataIntegrityViolationException e) {
      
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, id));
        } catch (EmptyResultDataAccessException e) {
      
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_COZINHA_NAO_ENCONTRADA, id));
        }
    }

    @Transactional
    public Cozinha buscarOuFalhar(Integer id) {
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(id));
    }

}
