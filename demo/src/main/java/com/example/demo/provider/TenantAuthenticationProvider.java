package com.example.demo.provider;

import com.example.demo.config.token.TenantAuthenticationToken;
import com.example.demo.service.ITenantAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TenantAuthenticationProvider implements AuthenticationProvider {

    private final ITenantAuthenticationService iTenantAuthenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return iTenantAuthenticationService.authenticate((TenantAuthenticationToken) authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(TenantAuthenticationToken.class);
    }
}
