package com.petcare.center.platform.contexts.shared.infrastructure.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configures the OpenAPI specification exposed by the PetCare Platform.
 */
@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI petcareOpenApi() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("/")
                                .description("Current environment")
                ))
                .info(new Info()
                        .title("PetCare Platform API")
                        .description("REST API for pet owners, veterinary clinics and mobile veterinary service providers.")
                        .version("v1")
                        .contact(new Contact()
                                .name("PetCare Development Team"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
