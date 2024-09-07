package com.example.demo.service.impl;

import com.example.demo.config.token.JWTAuthenticationToken;
import com.example.demo.config.token.TenantAuthenticationToken;
import com.example.demo.service.IJWTAuthenticationService;
import com.example.demo.service.ITenantAuthenticationService;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTAuthenticationServiceImpl implements IJWTAuthenticationService {

    @Override
    public JWTAuthenticationToken authenticate(JWTAuthenticationToken token) {
        return null;
    }
}
