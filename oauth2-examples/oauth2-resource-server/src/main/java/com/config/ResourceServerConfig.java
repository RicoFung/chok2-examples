package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import chok.oauth2.MyAccessDeniedHandler;
import chok.oauth2.MyOAuth2ExceptionEntryPoint;

@EnableWebSecurity(debug = false)
public class ResourceServerConfig
{
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests((requests) -> requests.anyRequest().authenticated()).oauth2ResourceServer()
		// 401 Invalid access token
		.authenticationEntryPoint(new MyOAuth2ExceptionEntryPoint())
		// 403 insufficient_scope
		.accessDeniedHandler(new MyAccessDeniedHandler()).jwt();
		return http.build();
	}

	// @Bean
	// SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	// http
	// .mvcMatcher("/test/**")
	// .authorizeRequests()
	// .mvcMatchers("/test/**").access("hasAuthority('SCOPE_test.read')")
	// .and()
	// .oauth2ResourceServer()
	// .jwt();
	// return http.build();
	// }
	// @Bean
	// SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	// http
	// .mvcMatcher("/articles/**")
	// .authorizeRequests()
	// .mvcMatchers("/articles/**").access("hasAuthority('SCOPE_articles.read')")
	// .and()
	// .oauth2ResourceServer()
	// .jwt();
	// return http.build();
	// }
	
    @Bean
    WebSecurityCustomizer ignore() {
        return web -> web.ignoring().antMatchers(
    	        "/",   
    	        "/swagger-ui",
    	        "/v3/api-docs/**",
    	        "/*.html",
    	        "/**/*.html",
    	        "/**/*.css",
    	        "/**/*.js",
    	        "/**/*.png",
    	        "/favicon.ico",
    	        "/error");
    }
}
