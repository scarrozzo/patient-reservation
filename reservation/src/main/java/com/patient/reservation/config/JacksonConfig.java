package com.patient.reservation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.core.util.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return JsonUtils.getObjectMapper();
    }
}
