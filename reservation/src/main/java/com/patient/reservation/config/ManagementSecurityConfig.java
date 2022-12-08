package com.patient.reservation.config;

import com.patient.core.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Order(90)
@Configuration
@EnableWebSecurity
public class ManagementSecurityConfig {

    private static final String MANAGEMENT_ROLE = "MANAGEMENT";
    public static final String DEFAULT_ACTUATOR_PATH = "/actuator";
    private static final String SWAGGER_UI_PATH = "/swagger-ui";
    private static final String API_DOCS_PATH = "/api-docs";

    @Value("${application.security.basic.username}")
    private String username;

    @Value("${application.security.basic.password}")
    private String password;

    @Autowired
    private Environment environment;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /**
         * OpenAPI endpoints
         */
        if (environment.acceptsProfiles(Profiles.of(Profile.PROD, Profile.PRE_PROD))) {
            http.authorizeHttpRequests().requestMatchers(DEFAULT_ACTUATOR_PATH + "/**", SWAGGER_UI_PATH + "/**", API_DOCS_PATH + "/**", SWAGGER_UI_PATH + ".html").denyAll();
        } else {
            http.authorizeHttpRequests().requestMatchers(DEFAULT_ACTUATOR_PATH + "/**", SWAGGER_UI_PATH + "/**", API_DOCS_PATH + "/**", SWAGGER_UI_PATH + ".html").permitAll();
        }

        /**
         * Protect all management endpoints
         */
        http.authorizeHttpRequests().anyRequest().hasRole(MANAGEMENT_ROLE);

        http.csrf().disable().sessionManagement().disable().httpBasic();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername(username)
                .password("{noop}" + password)
                .roles(MANAGEMENT_ROLE)
                .build());
        return manager;
    }
}