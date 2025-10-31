package org.cesarlead.customerservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
            title = "Configuracion",
            // v (version) 1 (Major). 0 (minor). 0 (patch)
            version = "v1.0.0",
            description = "Configuracion API",
            contact = @Contact(
                name = "Cesarlead",
                email = "cesarlead@emp.com",
                url = "hhtps://solucionecesarlead.com"
            )
        ),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Autenticacion basada en JWT (bearer)"
)
public class OpenApiConfig {
}
