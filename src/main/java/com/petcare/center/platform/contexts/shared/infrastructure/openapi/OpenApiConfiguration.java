package com.petcare.center.platform.contexts.shared.infrastructure.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI petcareOpenApi() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:3000").description("Local development")))
                .info(new Info()
                        .title("PetCare Platform API")
                        .description("REST API for pet owners, veterinary clinics and mobile veterinary service providers.")
                        .version("v1"));
    }
}
