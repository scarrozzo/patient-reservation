package com.patient.reservation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class OpenAPIConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.application.version}")
    private String applicationVersion;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title(applicationName.replace("-"," ").toUpperCase(Locale.ROOT))
                        .description("Service name: " + applicationName)
                        .version(applicationVersion)
                        .contact(new Contact()
                                .name("Sergio Carrozzo")
                                .url("https://github.com/scarrozzo")
                                .email("sergio.carrozzo@gmail.com")));
    }

}
