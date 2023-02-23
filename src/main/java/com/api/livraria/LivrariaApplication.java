package com.api.livraria;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Livraria",
                version = "1.0.0",
                description = "Esse projeto foi desenolvido para um sistema de Livraria.",
                contact = @Contact( name ="Abraao Wendel",url = "https://github.com/abraaowendel")
        )
)
public class LivrariaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LivrariaApplication.class, args);
    }
}
