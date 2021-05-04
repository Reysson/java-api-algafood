package com.algaworks.algafoodreysson.domain.service;

import com.algaworks.algafoodreysson.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodreysson.domain.exceptions.EstadoNaoEncontradoException;
import com.algaworks.algafoodreysson.domain.model.Estado;
import com.algaworks.algafoodreysson.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;
   
    
    private static final String MSG_ESTADO_EM_USO  = 
        "Estado de código %d não pode ser removido, pois está em uso";
    
    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }
    
    @Transactional
    public void deletar(Integer id) {

        try {
            estadoRepository.deleteById(id);
            estadoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException f) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, id));
        }

    }
    
    @Transactional
    public Estado buscarOuFalhar(Integer estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

}
