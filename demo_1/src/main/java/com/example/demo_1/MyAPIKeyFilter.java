//package com.example.demo_1;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//
//@Slf4j
//@Component
//@Order(-107)
//public class MyAPIKeyFilter implements Filter {
//
//    private final String apiKey;
//
//    public MyAPIKeyFilter() {
//        this.apiKey = Base64.getEncoder().encodeToString("password".getBytes(StandardCharsets.UTF_8));
//        log.info("ApiKey: {}", apiKey);
//    }
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String apiKeyHeader = ((HttpServletRequest) servletRequest).getHeader("X-API-KEY");
//        if (apiKeyHeader == null || !apiKeyHeader.equals(this.apiKey)) {
//            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
