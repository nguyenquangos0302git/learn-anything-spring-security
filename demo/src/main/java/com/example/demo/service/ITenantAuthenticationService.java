package com.example.demo.service;

import com.example.demo.config.token.TenantAuthenticationToken;

public interface ITenantAuthenticationService {

    TenantAuthenticationToken authenticate(TenantAuthenticationToken token);

}
