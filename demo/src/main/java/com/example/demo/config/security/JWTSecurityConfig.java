package com.example.demo.config.security;

import com.example.demo.entry.JWTEntryPoint;
import com.example.demo.entry.TenantEntryPoint;
import com.example.demo.filter.JWTAuthenticationFilter;
import com.example.demo.filter.TenantAuthenticationFilter;
import com.example.demo.provider.JWTAuthenticationProvider;
import com.example.demo.provider.TenantAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class JWTSecurityConfig {

    private static final List<String> endpoint = List.of(
            "/jwtAuth"
    );

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final TenantAuthenticationFilter tenantAuthenticationFilter;
    private final JWTAuthenticationProvider jwtAuthenticationProvider;
    private final TenantAuthenticationProvider tenantAuthenticationProvider;
    private final JWTEntryPoint jwtEntryPoint;

    @Bean
    @Order(2)
    public SecurityFilterChain jWTsecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(tenantAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .securityMatcher(endpoint.toArray(String[]::new))
                .authorizeHttpRequests(auth -> auth.requestMatchers(endpoint.toArray(String[]::new)).authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtEntryPoint))
//                .formLogin(Customizer.withDefaults())
//                .httpBasic().authenticationEntryPoint(jwtEntryPoint)
                ;

        return http.build();
    }



}
