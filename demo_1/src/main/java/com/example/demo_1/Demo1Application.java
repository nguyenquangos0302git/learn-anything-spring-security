package com.example.demo_1;

import jakarta.servlet.DispatcherType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

//	@Bean
//	public FilterRegistrationBean<MyAPIKeyFilter> myFilter() {
//		MyAPIKeyFilter myAPIKeyFilter = new MyAPIKeyFilter();
//		FilterRegistrationBean<MyAPIKeyFilter> registrationBean = new FilterRegistrationBean<>(myAPIKeyFilter);
//		registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
//		registrationBean.addUrlPatterns("/hello");
//		registrationBean.setOrder(-104);
//		return registrationBean;
//	}
//
//	@Bean
//	public FilterRegistrationBean<MyAPIKeyFilter> myFilter1() {
//		MyAPIKeyFilter myAPIKeyFilter = new MyAPIKeyFilter();
//		FilterRegistrationBean<MyAPIKeyFilter> registrationBean = new FilterRegistrationBean<>(myAPIKeyFilter);
//		registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
//		registrationBean.addUrlPatterns("/hello");
//		registrationBean.setOrder(-106);
//		return registrationBean;
//	}

//	@Bean
//	@Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
//	SecurityFilterChain apiKeySecurityFilterChain(HttpSecurity http) throws Exception {
//		http.securityMatchers(matchers -> matchers.requestMatchers("/api-key/**"));
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//		http.addFilterBefore(new ApiKeyFilter(), AuthorizationFilter.class);
//		return http.build();
//	}

//	@Bean
//	@Order(SecurityProperties.BASIC_AUTH_ORDER)
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//		http.formLogin(withDefaults());
//		http.httpBasic(withDefaults());
//		return http.build();
//	}

	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//		http.formLogin(withDefaults());
//		http.httpBasic(withDefaults());
		return http.build();
	}

}
