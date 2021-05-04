package com.algaworks.algafoodreysson.domain.service;

import com.algaworks.algafoodreysson.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodreysson.domain.exceptions.FormaPagamentoNaoEncontradaExeception;
import com.algaworks.algafoodreysson.domain.model.FormaPagamento;
import com.algaworks.algafoodreysson.domain.repository.FormaPagamentoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private static final String MSG_FORMA_PAGAMENTO_NAO_ENCONTRADA = "Não existe cadastro de forma de pagamento com código %d";

    private static final String MSG_FORMA_PAGAMENTO_EM_USO
            = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

    @Transactional
    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void deletar(Integer id) {
        try {
            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new FormaPagamentoNaoEncontradaExeception(
                    String.format(MSG_FORMA_PAGAMENTO_NAO_ENCONTRADA, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, id));
        }
    }

    @Transactional
    public FormaPagamento buscarOuFalhar(Integer id) {
        return formaPagamentoRepository.findById(id).orElseThrow(
                () -> new FormaPagamentoNaoEncontradaExeception(String.format(MSG_FORMA_PAGAMENTO_NAO_ENCONTRADA, id)));
    }
}
