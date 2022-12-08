package com.patient.reservation.config;

import com.patient.reservation.audit.CustomAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
        basePackages = {"com.patient.reservation.domain"},
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Repository")
)
public class PersistenceConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new CustomAuditorAware();
    }
}
