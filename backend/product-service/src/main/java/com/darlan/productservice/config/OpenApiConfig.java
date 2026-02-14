package com.darlan.productservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Product API")
                .version("v1")
                .description("API REST para cadastro de produtos")
                .contact(new Contact()
                    .name("Darlan")
                    .email("darlan@email.com")))
                .externalDocs(new ExternalDocumentation()
                    .description("Documentação do projeto")
                    .url("https://product-service.com/docs"));
    }
}
