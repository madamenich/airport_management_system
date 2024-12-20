package com.airline.airport_management_demo.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Airport Management System API")
//                        .description("API documentation for managing airports, flights, passengers, tickets, and users")
//                        .version("1.0.0"))
//                .components(new Components())
//                .addSecurityItem(new SecurityRequirement());
//    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                                .info(new Info()
                        .title("Airport Management System API")
                        .description("API documentation for managing airports, flights, passengers, tickets, and users")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}