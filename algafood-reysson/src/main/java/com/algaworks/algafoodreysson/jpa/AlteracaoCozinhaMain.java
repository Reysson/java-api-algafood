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
//public class AlteracaoCozinhaMain {
//    
//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodReyssonApplication.class)
//                    .web(WebApplicationType.NONE).run(args);
//        
//        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);
//        
//        Cozinha cozinha = new Cozinha();
//        cozinha.setId(1);
//        cozinha.setNome("Russa");
//        
//        cadastroCozinha.adicionar(cozinha);
//        
//    }
//}
