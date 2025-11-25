package com.freelancenexus.freelancer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.freelancenexus.freelancer.security.JwtAuthenticationFilter;

/**
 * Configuration class for Spring Security.
 * Configures security settings, including JWT authentication and method-level security.
 */
@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
	private final Logger log= LoggerFactory.getLogger(SecurityConfig.class);
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
//    @Autowired
//    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//		super();
//		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//	}

	/**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity object
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	log.info("security filter chain called");
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> {})
            .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                	    "/actuator/**",
                	    "/swagger-ui/**",
                	    "/swagger-ui.html",
                	    "/v3/api-docs/**",
                	    "/api-docs/**",
                	    "/swagger-resources/**",
                	    "/webjars/**",
                	    "/api/freelancers/**" 
                	).permitAll()
                	
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
    	return new JwtAuthenticationFilter();
    }
    
    @Bean
    public CorsFilter corsFilter() {

        log.info("Configuring CORS filter for http://localhost:3000");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // React frontend
        config.addAllowedOrigin("http://localhost:8765");
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        log.debug("CORS configuration applied: allowedOrigin=http://localhost:3000, allowedMethods=*");

        return new CorsFilter(source);
    }
}