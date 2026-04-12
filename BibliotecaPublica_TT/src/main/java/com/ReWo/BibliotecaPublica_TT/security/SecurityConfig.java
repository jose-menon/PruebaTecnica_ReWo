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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .userDetailsService(customUserDetailsService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // preflight CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // públicos
                        .requestMatchers("/auth/**").permitAll()

                        // catálogo de libros: público
                        .requestMatchers(HttpMethod.GET, "/api/libros/**").permitAll()

                        // registro de usuarios
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()

                        // reportes: solo admin
                        .requestMatchers("/api/reportes/**").hasRole("ADMIN")

                        // multas: admin o usuario
                        .requestMatchers("/api/multas/**").hasAnyRole("ADMIN", "USUARIO")

                        // préstamos y reservas: admin o usuario
                        .requestMatchers("/api/prestamos/**").hasAnyRole("ADMIN", "USUARIO")
                        .requestMatchers("/api/reservas/**").hasAnyRole("ADMIN", "USUARIO")

                        // gestión de usuarios: solo admin
                        .requestMatchers("/api/usuarios/**").hasRole("ADMIN")

                        // CRUD de libros: solo admin
                        .requestMatchers(HttpMethod.POST, "/api/libros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/libros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/libros/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                "https://bibliotecapublica.vercel.app",
                "http://localhost:5173",
                "http://127.0.0.1:5173"
        ));

        configuration.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));

        configuration.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "Accept",
                "Origin",
                "X-Requested-With"
        ));

        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
