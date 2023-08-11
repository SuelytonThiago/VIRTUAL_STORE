package com.example.virtalStore.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private JWTFilter filter;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()

                .requestMatchers(HttpMethod.POST, "/api/clients/newAccount").permitAll()

                .requestMatchers(
                        "/api/v1/auth/**",
                        "/v2/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                ).permitAll()

                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/refresh").hasAnyRole("ADMIN", "CLIENT")
                .requestMatchers(HttpMethod.POST, "/api/admins").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/categories").hasAnyRole("CLIENT", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/categories").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/clients/addAddress").hasRole("CLIENT")

                .requestMatchers(HttpMethod.GET, "/api/orders").hasRole("CLIENT")
                .requestMatchers(HttpMethod.POST, "/api/orders").hasRole("CLIENT")

                .requestMatchers(HttpMethod.GET, "/api/products").hasAnyRole("CLIENT", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/products/findByCategory").hasAnyRole("CLIENT", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN")
                .anyRequest().authenticated().and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
        }
    }

