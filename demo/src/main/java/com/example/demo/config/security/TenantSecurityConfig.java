package com.example.demo.config.security;

import com.example.demo.entry.TenantEntryPoint;
import com.example.demo.filter.TenantAuthenticationFilter;
//import com.example.demo.provider.TenantAuthenticationProvider;
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

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class TenantSecurityConfig {

    private static final List<String> endpoint = List.of(
            "/tenantAuth"
    );

    private final TenantAuthenticationFilter tenantAuthenticationFilter;
    private final TenantEntryPoint tenantEntryPoint;
    private final TenantAuthenticationProvider tenantAuthenticationProvider;
    private final JWTAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    @Order(1)
    public SecurityFilterChain tenantsecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(tenantAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .securityMatcher(endpoint.toArray(String[]::new))
                .authorizeHttpRequests(auth -> auth.requestMatchers(endpoint.toArray(String[]::new)).authenticated())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(tenantEntryPoint))
//                .formLogin(Customizer.withDefaults())
                ;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return new ProviderManager(tenantAuthenticationProvider, jwtAuthenticationProvider, new DaoAuthenticationProvider());
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(tenantAuthenticationProvider);
//        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
//        return authenticationManagerBuilder.build();
    }

}
