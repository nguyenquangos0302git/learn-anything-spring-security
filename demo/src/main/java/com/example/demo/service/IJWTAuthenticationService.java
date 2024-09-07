package com.example.demo.service;

import com.example.demo.config.token.JWTAuthenticationToken;
import com.example.demo.config.token.TenantAuthenticationToken;

public interface IJWTAuthenticationService {

    JWTAuthenticationToken authenticate(JWTAuthenticationToken token);

}
