package com.algaworks.algafoodreysson.api.controller;

import com.algaworks.algafoodreysson.api.dto.FormaPagamentoDto;
import com.algaworks.algafoodreysson.api.dto.RestauranteDto;
import com.algaworks.algafoodreysson.api.dto.assembler.FormaPagamentoDtoAssembler;
import com.algaworks.algafoodreysson.api.dto.assembler.RestauranteDtoAssembler;
import com.algaworks.algafoodreysson.api.dto.assembler.RestauranteDtoInputDesasembler;
import com.algaworks.algafoodreysson.api.dto.input.RestauranteInputDto;
import com.algaworks.algafoodreysson.domain.exceptions.CidadeNaoEncontradaException;
import com.algaworks.algafoodreysson.domain.exceptions.CozinhaNaoEncontradaException;
import com.algaworks.algafoodreysson.domain.exceptions.NegocioException;
import com.algaworks.algafoodreysson.domain.model.Restaurante;
import com.algaworks.algafoodreysson.domain.repository.RestauranteRepository;
import com.algaworks.algafoodreysson.domain.service.RestauranteService;
import com.algaworks.algafoodreysson.infraestrutura.repository.spec.RestauranteComFreteGratisSpec;
import com.algaworks.algafoodreysson.infraestrutura.repository.spec.RestauranteComNomeSemelhanteSpec;
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
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;
    
    @Autowired
    private RestauranteDtoAssembler restauranteDtoAssembler;
    
    @Autowired
    private RestauranteDtoInputDesasembler restauranteDtoDesassembler;
    
    @Autowired
    private FormaPagamentoDtoAssembler formaAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<RestauranteDto> listar() {
        return restauranteDtoAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{id}")
    public RestauranteDto buscar(@PathVariable Integer id) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(id);
        
        RestauranteDto restauranteDto = restauranteDtoAssembler.toModel(restaurante);
        
        return restauranteDto;
    }


    @GetMapping("/taxa-frete")
    public List<Restaurante> buscarPorTaxa(Float taxaInicial, Float taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/teste")
    public List<Restaurante> teste(String nomeCozinha) {
        return restauranteRepository.buscarRestaurantePorCozinha(nomeCozinha);
    }

    @GetMapping("/com-frete-gratis")
    public List<Restaurante> buscarPorTaxaFretegratisSpec(String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

        return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
    }

    @GetMapping("/por-cozinha")
    public List<Restaurante> buscarRestaurantePorCozinha(String nome, Integer id) {
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, id);

    }

    @GetMapping("/por-cozinha-novo")
    public List<Restaurante> buscarNomePorCozinha(String nome, Integer id) {
        return restauranteRepository.consultaPorNome(nome, id);
    }

    @GetMapping("/por-cozinha-taxa")
    public List<Restaurante> buscarPorCozinhaTaxaEntrega(String nome, Float taxaFreteInicial, Float taxaFreteFinal) {
        return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDto adicionar(@RequestBody @Valid RestauranteInputDto restauranteInputDto) {
        Restaurante restaurante = restauranteDtoDesassembler.toDomainObject(restauranteInputDto);
        return restauranteDtoAssembler.toModel(restauranteService.salvar(restaurante));
    }

    @PutMapping("/{id}")
    public RestauranteDto atualizar(@PathVariable Integer id, 
            @RequestBody @Valid RestauranteInputDto restauranteInputDto) {

        try{
            
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(id);

            restauranteDtoDesassembler.copyToDomainObject(restauranteInputDto, restauranteAtual);

            return restauranteDtoAssembler.toModel(restauranteService.salvar(restauranteAtual));
        
        }catch(CozinhaNaoEncontradaException  | CidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }

    }
    
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/ativo")
    public void ativar(@PathVariable Integer id){
        restauranteService.ativar(id);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/ativo")
    public void desativar(@PathVariable Integer id){
        restauranteService.desativar(id);
    }
    
    
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{idRestaurante}/forma-pagamento")
    public List<FormaPagamentoDto> listarFormaPagamentoRestaurante(@PathVariable Integer idRestaurante){
        Restaurante restaurante = restauranteService.buscarOuFalhar(idRestaurante);
        
        return formaAssembler.toCollectionModel(restaurante.getFormasPagamento());
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/{idRestaurante}/forma-pagamento/{idFormaPagamento}")
    public void associarFormaPagamento(@PathVariable Integer idRestaurante, @PathVariable Integer idFormaPagamento){
        restauranteService.adicionarFormaPagamento(idRestaurante, idFormaPagamento);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{idRestaurante}/forma-pagamento/{idFormaPagamento}")
    public void desassociarFormaPagamento(@PathVariable Integer idRestaurante, @PathVariable Integer idFormaPagamento){
        restauranteService.desassociarFormaPagamento(idRestaurante, idFormaPagamento);
    }
    

}
