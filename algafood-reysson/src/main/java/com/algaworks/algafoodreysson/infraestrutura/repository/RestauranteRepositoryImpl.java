package com.algaworks.algafoodreysson.infraestrutura.repository;

import com.algaworks.algafoodreysson.domain.repository.RestauranteRepositoryQuery;
import com.algaworks.algafoodreysson.domain.model.Restaurante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuery {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Restaurante> find(String nome, Float taxaFreteInicial, Float taxaFreteFinal) {

        /*1 forma menos complexa*/
//        var jpql = "from Restaurante where nome like :nome "
//                + "and taxaFrete between :taxaInicial and :taxaFinal";
//        
//        return manager.createQuery(jpql, Restaurante.class)
//                .setParameter("nome", "%" + nome + "%")
//                .setParameter("taxaInicial", taxaFreteInicial)
//                .setParameter("taxaFinal", taxaFreteFinal)
//                .getResultList();

        /*2 Forma mais complexa com verificacoes de valores possivelmente nulos*/
        
//        var jpql = new StringBuilder();
//        
//        var parametros = new HashMap<String, Object>();
//        
//        jpql.append("from Restaurante where 0 = 0 ");
//
//        if (StringUtils.hasLength(nome)) {
//            jpql.append("and nome like :nome ");
//            parametros.put("nome", "%" + nome + "%");
//        }
//        if(taxaFreteInicial != null){
//            jpql.append("and taxaFrete >= :taxaInicial ");
//            parametros.put("taxaInicial", taxaFreteInicial);
//        }
//        if(taxaFreteInicial != null){
//            jpql.append("and taxaFrete <= :taxaFinal");
//            parametros.put("taxaFinal", taxaFreteFinal);
//            
//        }
//        
//        TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class); 
//        
//        parametros.forEach((chave,valor) -> query.setParameter(chave, valor));
//        
//        return query.getResultList();
          
          /*3º Forma com CRITERIA*/
            
//            /*1º Vamos criar o buider da criteria*/
//            CriteriaBuilder builder = manager.getCriteriaBuilder();
//            
//            /*2º Vamos criar as clausulas*/
//            CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
//            
//            /*3º Vamos adicionar as clausulas*/
//            criteria.from(Restaurante.class);
//            
//            /*4° Adicionar a criteria na instancia EntityManager e retorna-la*/
//            TypedQuery<Restaurante> query = manager.createQuery(criteria);
//            
//            return query.getResultList();
            
          /*4° Forma CRITERIA complexa*/
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            
           
            CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
            Root<Restaurante> root = criteria.from(Restaurante.class);
            
            //Vamos criar as clausulas dinamicamente
            var predicates = new ArrayList<Predicate>();
            
            if(StringUtils.hasLength(nome)){
                predicates.add(builder.like(root.get("nome"),"%" + nome + "%"));
            }
            if(taxaFreteInicial != null){
                predicates.add(builder.
                            greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
            }
            if(taxaFreteFinal != null){
                predicates.add(builder.
                    lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
            
            }
            
            
            //vamos adcionar as clausulas convertendo List em Array
            criteria.where(predicates.toArray(new Predicate[0]));
            
            
            TypedQuery<Restaurante> query = manager.createQuery(criteria);
          
            return query.getResultList();
    }
    
    @Override
    public List<Restaurante> buscarRestaurantePorCozinha(String nomeCozinha){
           
        var jpql = "from Restaurante where cozinha.nome like :cozinha";
        
        return manager.createQuery(jpql,Restaurante.class)
                .setParameter("cozinha", "%" + nomeCozinha + "%")
                .getResultList(); 
          
//          CriteriaBuilder builder = manager.getCriteriaBuilder();
//          CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
//          
//          Root<Restaurante> root = criteria.from(Restaurante.class); 
//          
//          //clausulas
//          var cozinhaPredicate = builder.like(root.get("cozinha.nome"), "%" + nomeCozinha + "%");
//          
//          criteria.where(cozinhaPredicate);
//          
//          return manager.createQuery(criteria).getResultList();
            
          
    }
}
