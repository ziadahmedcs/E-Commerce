package com.Hr.Market.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;
    // Token
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        ))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/Register").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/Data").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST ,"/Items/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST ,"/Order/orders/**").hasAnyRole("ADMIN" ,"EMPLOYEE")
                        .requestMatchers(HttpMethod.POST ,"/Items/category/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT ,"/Items/category/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE ,"/Items/category/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET ,"/Items/category/**").hasAnyRole("ADMIN","EMPLOYEE")
                        .requestMatchers(HttpMethod.GET ,"/Items/products/search/**").hasAnyRole("ADMIN","EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT ,"/Items/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/Items/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/Items/products/**").hasAnyRole("ADMIN","EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}