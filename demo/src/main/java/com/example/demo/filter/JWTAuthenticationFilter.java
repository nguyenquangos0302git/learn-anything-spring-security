package com.example.demo.filter;

import com.example.demo.config.token.JWTAuthenticationToken;
import com.example.demo.config.token.TenantAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String clientId = request.getHeader("Bearer ");
        String clientSecret = request.getHeader("X-Client-Secret");
        if(clientId == null || clientSecret == null) {
            logger.trace("Did not find client id or client secret in request");
            filterChain.doFilter(request, response);
            return;
        }
        try {
            JWTAuthenticationToken authentication = (JWTAuthenticationToken) this.authenticationManager.authenticate(new JWTAuthenticationToken(clientId));
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
