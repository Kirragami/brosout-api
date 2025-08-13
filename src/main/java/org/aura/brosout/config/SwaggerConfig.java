package org.aura.brosout.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BROSOUT Application API")
                        .description("BROSOUT Application API Documentation")
                        .version("1.0")
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearer-token")
                )
                .components(
                        new Components()
                                .addSecuritySchemes("bearer-token", bearerToken())
                );
    }

    private SecurityScheme bearerToken() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
