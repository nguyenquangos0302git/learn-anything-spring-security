package com.example.demo.config.security;

import com.example.demo.entry.JWTEntryPoint;
import com.example.demo.filter.JWTAuthenticationFilter;
import com.example.demo.filter.TenantAuthenticationFilter;
import com.example.demo.provider.JWTAuthenticationProvider;
import com.example.demo.provider.TenantAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.intercept.AfterInvocationManager;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.access.intercept.RunAsManager;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.config.Elements;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class NoAuthSecurityConfig {

    private static final List<String> endpoint = List.of(
            "/noauth"
    );

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final TenantAuthenticationFilter tenantAuthenticationFilter;
    private final JWTAuthenticationProvider jwtAuthenticationProvider;
    private final TenantAuthenticationProvider tenantAuthenticationProvider;
    private final JWTEntryPoint jwtEntryPoint;

    @Bean
    @Order(3)
    public SecurityFilterChain noAuthsecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityMatcher(endpoint.toArray(String[]::new))
                .authorizeHttpRequests(auth -> auth.requestMatchers(endpoint.toArray(String[]::new)).permitAll())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtEntryPoint))
//                .formLogin(Customizer.withDefaults())
//                .httpBasic().authenticationEntryPoint(jwtEntryPoint)
        ;

        return http.build();
    }

}
