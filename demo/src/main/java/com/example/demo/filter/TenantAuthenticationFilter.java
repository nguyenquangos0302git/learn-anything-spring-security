package com.example.demo.filter;

import com.example.demo.config.token.TenantAuthenticationToken;
import com.example.demo.service.ITenantAuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class TenantAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    public TenantAuthenticationFilter(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String clientId = request.getHeader("X-Client-Id");
        String clientSecret = request.getHeader("X-Client-Secret");
        if(clientId == null || clientSecret == null) {
            logger.trace("Did not find client id or client secret in request");
            filterChain.doFilter(request, response);
            return;
        }
        try {
            TenantAuthenticationToken authentication = (TenantAuthenticationToken) this.authenticationManager.authenticate(new TenantAuthenticationToken(clientId, clientSecret));
//            TenantAuthenticationToken tenantAuthenticationToken = iTenantAuthenticationService.authenticate(new TenantAuthenticationToken(clientId, clientSecret));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            this.logger.error("Tenant Authentication failed");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // If you want to immediatelly return an error response, you can do it like this:
//             response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            // but you can also just let the request go on
            filterChain.doFilter(request, response);
        }
    }

}
