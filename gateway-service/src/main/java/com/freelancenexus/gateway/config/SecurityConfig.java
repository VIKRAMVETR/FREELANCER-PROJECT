package com.freelancenexus.gateway.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * Security Configuration for API Gateway
 * 
 * This configuration is simplified without Keycloak.
 * JWT validation is handled by AuthenticationFilter.
 * 
 * Public Endpoints (no authentication required):
 * - User registration and login
 * - Public freelancer profiles
 * - Public project listings
 * - Health check endpoints
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * Configure Security Filter Chain
     * 
     * Disables Spring Security's default authentication since
     * we're using custom JWT validation in AuthenticationFilter.
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())
            .formLogin(formLogin -> formLogin.disable())
            .httpBasic(httpBasic -> httpBasic.disable())
            .authorizeExchange(exchanges -> exchanges
                .anyExchange().permitAll() // Allow all - AuthenticationFilter handles auth
            );

        return http.build();
    }
    
   
    @Bean
    public CorsFilter corsFilter() {

//        logger.info("Configuring CORS filter for http://localhost:3000");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:8082");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

//        log.debug("CORS configuration applied: allowedOrigin=http://localhost:3000, allowedMethods=*");

        return new CorsFilter(source);
    }
}