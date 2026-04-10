package com.ReWo.BibliotecaPublica_TT.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig
{
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(csrf -> csrf.disable())
                .userDetailsService(customUserDetailsService)
                .authorizeHttpRequests(auth ->auth
                //Publicos
                        .requestMatchers("/auth/**").permitAll()
                //catalogo de libros: para publicos y autorizados
                        .requestMatchers(HttpMethod.GET, "/api/libros/**").permitAll()
                //admin reportes
                        .requestMatchers("/api/reportes/**").hasRole("ADMIN")
                //multas: solo autenticados
                        .requestMatchers("/api/multas/**").hasAnyRole("ADMIN", "USUARIO")
                //prestamos y reservas: admin o usuario
                        .requestMatchers("/api/prestamos/**").hasAnyRole("ADMIN", "USUARIO")
                        .requestMatchers("/api/reservas/**").hasAnyRole("ADMIN", "USUARIO")
                //registro de usuarios
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                //gestiones de usuario solo admin
                        .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                //CRUD de libros solo admin
                        .requestMatchers(HttpMethod.POST, "/api/libros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/libros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/libros/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
    {
        return configuration.getAuthenticationManager();
    }
}
