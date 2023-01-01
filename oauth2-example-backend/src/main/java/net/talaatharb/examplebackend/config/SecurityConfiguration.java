package net.talaatharb.examplebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http // HTTP Security
                .cors().and() // CORS policy
                .csrf().disable() // CSRF disable
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // disable
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/swagger-ui/**").permitAll()
                ).oauth2ResourceServer().jwt();

        return http.build();
    }
}
