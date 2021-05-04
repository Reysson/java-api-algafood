//package com.algaworks.algafoodreysson.jpa;
//
//import com.algaworks.algafoodreysson.AlgafoodReyssonApplication;
//import com.algaworks.algafoodreysson.domain.model.Cozinha;
//import com.algaworks.algafoodreysson.domain.repository.CozinhaRepository;
//import java.util.List;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ApplicationContext;
//
//public class InclusaoCozinhaMain {
//    
//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodReyssonApplication.class)
//                    .web(WebApplicationType.NONE).run(args);
//        
//        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);
//        
//        Cozinha cozinha1 = new Cozinha();
//        Cozinha cozinha2 = new Cozinha();
//        
//        cozinha1.setNome("Africana");
//        cozinha2.setNome("Niponica");
//        
//        cozinha1 = cadastroCozinha.adicionar(cozinha1);
//        cozinha2 = cadastroCozinha.adicionar(cozinha2);
//        
//        System.out.printf("%d - %s ",cozinha1.getId(), cozinha1.getNome());
//        System.out.printf("%d - %s ",cozinha2.getId(), cozinha2.getNome());
//        
//        
//        
//    }
//}
