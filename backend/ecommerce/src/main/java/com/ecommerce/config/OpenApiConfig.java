package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuración para la documentación de la API con OpenAPI/Swagger.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configura la información básica de la API para Swagger.
     * @return Instancia de OpenAPI con la configuración.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ecommerce API")
                        .version("1.0.0")
                        .description("Documentación de la API para la aplicación de Ecommerce")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Equipo de Desarrollo")
                                .email("dev@ecommerce.com")));
    }
}