package com.algaworks.algafoodreysson;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlgafoodReyssonApplication {

    public static void main(String[] args) {
        
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(AlgafoodReyssonApplication.class, args);
    }

}
