package com.algaworks.algafoodreysson.domain.service;

import com.algaworks.algafoodreysson.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodreysson.domain.model.Cidade;
import com.algaworks.algafoodreysson.domain.model.Cozinha;
import com.algaworks.algafoodreysson.domain.model.FormaPagamento;
import com.algaworks.algafoodreysson.domain.model.Restaurante;
import com.algaworks.algafoodreysson.domain.repository.RestauranteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO
            = "Não existe cadastro de restaurante com código %d";

    /*ANTES VERSAO < JAVA 8*/
//    public Restaurante salvar(Restaurante restaurante){
//        Integer idCozinha = restaurante.getCozinha().getId();
//        Optional<Cozinha> cozinha = cozinhaRepository.findById(idCozinha);
//        if(cozinha == null){
//            throw new EntidadeNaoEncontradaException(
//                String.format("Não existe cadastro de cozinha com código %d", idCozinha));
//        }
//        
//        restaurante.setCozinha(cozinha);
//        return restauranteRepository.adicionar(restaurante);
//    }
    /*DEPOIS AGORA COM VERSAO >= JAVA 8*/
    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Integer idCozinha = restaurante.getCozinha().getId();

        Integer idCidade = restaurante.getEndereco().getCidade().getId();

        Cidade cidade = cidadeService.buscarOuFalhar(idCidade);

        Cozinha cozinha = cozinhaService.buscarOuFalhar(idCozinha);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Integer id) {
        Restaurante restaurante = buscarOuFalhar(id);

        restaurante.ativar();
    }

    @Transactional
    public void desativar(Integer id) {
        Restaurante restaurante = buscarOuFalhar(id);

        restaurante.desativar();
    }

    @Transactional
    public void adicionarFormaPagamento(Integer idRestaurante, Integer idFormaPagamento) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(idFormaPagamento);
        Restaurante restaurante = buscarOuFalhar(idRestaurante);

        restaurante.getFormasPagamento().add(formaPagamento);
    }

    @Transactional
    public void desassociarFormaPagamento(Integer idRestaurante, Integer idFormaPagamento) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(idFormaPagamento);
        Restaurante restaurante = buscarOuFalhar(idRestaurante);

        restaurante.getFormasPagamento().remove(formaPagamento);
    }

    @Transactional
    public Restaurante buscarOuFalhar(Integer id) {
        return restauranteRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id)));
    }

}
