package com.example.demo.provider;

import com.example.demo.config.token.JWTAuthenticationToken;
import com.example.demo.config.token.TenantAuthenticationToken;
import com.example.demo.service.IJWTAuthenticationService;
import com.example.demo.service.ITenantAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final IJWTAuthenticationService ijwtAuthenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return ijwtAuthenticationService.authenticate((JWTAuthenticationToken) authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JWTAuthenticationToken.class);
    }
}
