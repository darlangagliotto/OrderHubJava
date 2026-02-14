package com.darlan.clientservice.config;

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
                .title("Client API")
                .version("v1")
                .description("API REST para cadastro de clientes")
                .contact(new Contact()
                    .name("Darlan")
                    .email("darlan@email.com")))
                .externalDocs(new ExternalDocumentation()
                    .description("Documentação do projeto")
                    .url("https://client-service.com/docs"));
    }
}
