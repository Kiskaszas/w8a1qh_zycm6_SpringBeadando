package org.example.kaszmaginnovate.config;

import org.example.kaszmaginnovate.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());

        AuthenticationManager authenticationManager = authManagerBuilder.build();

        http
                .authenticationManager(authenticationManager)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // Swagger és API dokumentációhoz csak ADMIN hozzáférhet
                                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").hasRole("ADMIN")
                                // Publikus végpontok
                                .requestMatchers("/", "/login", "/register", "/contact", "/css/**", "/js/**", "/img/**").permitAll()
                                // API végpontok Basic Auth védelmével
                                .requestMatchers("/contact/send-message", "/api/**").authenticated()
                                .anyRequest().authenticated()
                )
                // Form alapú bejelentkezés a webes felülethez
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                // Basic Auth az API hívásokhoz
                .httpBasic(httpBasic -> httpBasic
                        .authenticationEntryPoint(new MyBasicAuthenticationEntryPoint())
                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/")
                )
                // CSRF kikapcsolása API hívásokhoz
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}