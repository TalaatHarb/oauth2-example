package net.talaatharb.examplebackend.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	private final Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthConverter;

	@Bean
	SecurityFilterChain filterChain(final HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

		http // HTTP Security
				.cors(withDefaults()) // CORS policy
				.csrf(withDefaults()) // CSRF disable
				.sessionManagement(withDefaults()).authorizeHttpRequests(requests -> requests //
						.requestMatchers(mvcMatcherBuilder.pattern("/api/**")).authenticated() //
						.requestMatchers(mvcMatcherBuilder.pattern("/swagger-ui/**")).permitAll() //
						.requestMatchers(mvcMatcherBuilder.pattern("/v3/api-docs/**")).permitAll() //
						.requestMatchers(mvcMatcherBuilder.pattern("/actuator/**")).permitAll()); //
		
		http.oauth2ResourceServer(oauth2 -> oauth2.jwt(j -> j.jwtAuthenticationConverter(jwtAuthConverter)));

		return http.build();
	}
}
