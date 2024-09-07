package com.example.demo.service.impl;

import com.example.demo.config.token.TenantAuthenticationToken;
import com.example.demo.service.ITenantAuthenticationService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.stereotype.Service;

@Service
public class TenantAuthenticationServiceImpl implements ITenantAuthenticationService {
    @Override
    public TenantAuthenticationToken authenticate(TenantAuthenticationToken token) {
        System.out.println(token.getClientId());
        System.out.println(token.getClientSecret());
        throw new ProviderNotFoundException("test");
    }
}
