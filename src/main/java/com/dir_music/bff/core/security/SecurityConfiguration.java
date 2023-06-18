package com.dir_music.bff.core.security;

import com.dir_music.bff.feign_controller.auth_controller.AuthenticationControllerFeign;
import com.dir_music.bff.feign_controller.auth_controller.input.TokenValidationControllerInput;
import com.dir_music.bff.feign_controller.auth_controller.output.TokenValidationControllerOutput;
import feign.FeignException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebMvc
public class SecurityConfiguration {

    final AuthenticationControllerFeign authenticationController;

    @Autowired
    public SecurityConfiguration(AuthenticationControllerFeign authenticationController) {
        this.authenticationController = authenticationController;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors();
        http.csrf().disable();

        http.addFilterBefore(this::jwtFilterChain, UsernamePasswordAuthenticationFilter.class);
        http.authorizeHttpRequests()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/refresh").permitAll()
                .requestMatchers("/users/register").permitAll()
                .requestMatchers("/search").authenticated()
                .requestMatchers("/songs/create").hasRole("ADMIN")
                .requestMatchers("/songs/delete").hasRole("ADMIN")
                .requestMatchers("/musicArt/upload").hasRole("ADMIN")
                .requestMatchers("/musicArt/delete").hasRole("ADMIN")
                .requestMatchers("/songs/isAvailable").permitAll()
                .anyRequest().authenticated();
        return http.build();
    }

    protected void jwtFilterChain(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String accessToken = ((HttpServletRequest) request).getHeader("Authorization");

        if (accessToken == null || accessToken.isEmpty()) {
            Authentication auth = new CustomAuthentication(
                    null,
                    "UNAUTHORIZED",
                    false
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(request, response);
            return;
        }

        final TokenValidationControllerInput tokenValidationControllerInput = TokenValidationControllerInput.builder()
                .accessToken(accessToken)
                .build();

        final ResponseEntity<TokenValidationControllerOutput> tokenValidationControllerOutputResponseEntity;
        try {
            tokenValidationControllerOutputResponseEntity =
                    this.authenticationController.getClaims(tokenValidationControllerInput);
        } catch (FeignException e) {
            ((HttpServletResponse) response).sendError(e.status(), e.getMessage());
            return;
        }

        if (tokenValidationControllerOutputResponseEntity.getStatusCode().is2xxSuccessful() &&
                tokenValidationControllerOutputResponseEntity.getBody() != null) {
            Authentication auth = new CustomAuthentication(
                    Long.parseLong(tokenValidationControllerOutputResponseEntity.getBody().getUsername()),
                    tokenValidationControllerOutputResponseEntity.getBody().getRole(),
                    true
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

            chain.doFilter(request, response);
        } else if (tokenValidationControllerOutputResponseEntity.getStatusCode() == HttpStatus.FORBIDDEN) {
            ((HttpServletResponse) response).sendError(HttpStatus.FORBIDDEN.value(), "Expired token");
        } else {
            ((HttpServletResponse) response).sendError(HttpStatus.BAD_REQUEST.value(), "Request Failed");
        }

    }

    public AuthenticationProvider authenticationProvider() {
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        };
    }

}
