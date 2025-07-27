package com.github.erodriguezg.grpcjavalab.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite todas las solicitudes sin autenticación
                )
                .formLogin(form -> form // Reemplazo de withDefaults()
                        .loginPage("/login") // Página de login personalizada
                        .defaultSuccessUrl("/urls", true) // Redirige tras login exitoso
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true) // Invalida la sesión
                        .deleteCookies("JSESSIONID", "SESSION") // limina cookies de sesión
                        .permitAll())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**")); // Permite llamadas API sin CSRF
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
