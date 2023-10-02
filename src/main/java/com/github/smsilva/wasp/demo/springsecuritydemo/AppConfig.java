package com.github.smsilva.wasp.demo.springsecuritydemo;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    WebMvcConfigurer cors() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:3000");
            }
            
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors
                        .disable())
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/api/external").permitAll()
                        .requestMatchers("/api/public").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .oauth2ResourceServer((oauth2) -> oauth2
                    .jwt(withDefaults()))
                .build();
    }

}
