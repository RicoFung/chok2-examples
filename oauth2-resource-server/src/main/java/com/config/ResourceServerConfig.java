package com.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.config.oauth2.CusAccessDeniedHandler;
import com.config.oauth2.CusOAuth2ExceptionEntryPoint;

@EnableWebSecurity(debug = false)
public class ResourceServerConfig
{
	@Bean
	public CorsConfigurationSource corsConfigurationSource()
	{
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // 对所有url生效
		return source;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.securityMatcher("/api/v3_1/customtbdemo/**")
				.authorizeHttpRequests(authorize ->
						authorize.requestMatchers("/api/v3_1/customtbdemo/**").hasAuthority("SCOPE_test.read")
				)
				.oauth2ResourceServer(oauth2ResourceServer ->
						oauth2ResourceServer.jwt(Customizer.withDefaults())
				);
		return http.build();
	}

//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//	{
//		http
//		.authorizeRequests((requests) -> requests.anyRequest().authenticated()).oauth2ResourceServer()
//		// 401 Invalid access token
//		.authenticationEntryPoint(new CusOAuth2ExceptionEntryPoint())
//		// 403 insufficient_scope
//		.accessDeniedHandler(new CusAccessDeniedHandler()).jwt();
//		return http.build();
//	}

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
        return web -> web.ignoring().requestMatchers(
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
