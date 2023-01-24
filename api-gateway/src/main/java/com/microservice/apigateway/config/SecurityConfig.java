package com.microservice.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    // Enable security for ApiGateway
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.csrf().disable()
                .authorizeExchange(authorizeExchangeSpec ->
                    authorizeExchangeSpec.pathMatchers("/eureka/**") // permit all route to eureka dashboard
                            .permitAll()
                            .anyExchange()
                            .authenticated())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        // enable jwt capabilities to access jwt from class OAuth2ResourceServerSpec
        return serverHttpSecurity.build();
    }
}
