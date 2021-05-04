package com.algaworks.algafoodreysson.domain.service;

import com.algaworks.algafoodreysson.domain.exceptions.CidadeNaoEncontradaException;
import com.algaworks.algafoodreysson.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodreysson.domain.model.Cidade;
import com.algaworks.algafoodreysson.domain.model.Estado;
import com.algaworks.algafoodreysson.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe cadastro de cidade com código %d";

    private static final String MSG_CIDADE_EM_USO
            = "Cidade de código %d não pode ser removida, pois está em uso";
    
    @Transactional
    public Cidade salvar(Cidade cidade) {

        Integer idEstado = cidade.getEstado().getId();

        Estado estadoBuscado = estadoService.buscarOuFalhar(idEstado);

        cidade.setEstado(estadoBuscado);
        return cidadeRepository.save(cidade);
    }
    
    @Transactional
    public void deletar(Integer id) {
        try {
            cidadeRepository.deleteById(id);
            cidadeRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADA, id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, id));
        }
    }
    
    @Transactional
    public Cidade buscarOuFalhar(Integer id) {
        return cidadeRepository.findById(id).orElseThrow(
                () -> new CidadeNaoEncontradaException(
                        String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
    }
}
