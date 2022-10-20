/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.authentication.LoginFilterSecurityConfigurer;
import com.authentication.cus.service.CusUserInfo0aService;
import com.domain.erp.service.TbUserInfo0aService;
import com.handler.RedirectLoginAuthenticationSuccessHandler;
import com.handler.SimpleAuthenticationEntryPoint;

@EnableWebSecurity(debug = true)
public class DefaultSecurityConfig
{
//	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//	private AuthenticationSuccessHandler authenticationSuccessHandler = this::sendAuthorizationResponse;
//	private AuthenticationFailureHandler authenticationFailureHandler = this::sendErrorResponse;
	
	@Autowired
	TbUserInfo0aService		tbUserInfo0aService;
	@Autowired
	CusUserInfo0aService	cusUserInfo0aService;
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource()
	{
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// 对所有url生效
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE + 1)
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, @Qualifier("authorizationServerSecurityFilterChain") SecurityFilterChain securityFilterChain) throws Exception
	{
		DefaultSecurityFilterChain authorizationServerFilterChain = (DefaultSecurityFilterChain) securityFilterChain;
		SimpleAuthenticationEntryPoint authenticationEntryPoint = new SimpleAuthenticationEntryPoint();
        AuthenticationEntryPointFailureHandler authenticationFailureHandler = new AuthenticationEntryPointFailureHandler(authenticationEntryPoint);
        RedirectLoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler = new RedirectLoginAuthenticationSuccessHandler();

		// ---------- //
		// 自定义登录配置
		// ---------- //
		http
		.requestMatcher(new AndRequestMatcher(new NegatedRequestMatcher(authorizationServerFilterChain.getRequestMatcher())))
		.csrf().disable()
		.formLogin()
		// 自定义登录页
		.loginPage("/login")
		// 自定义登录页拦截路径
		.loginProcessingUrl("/login")
//		.successHandler(new MyAuthenticationSuccessHandler())
//		.failureHandler(new MyAuthenticationFailureHandler()).permitAll()
		.and()
		// 放开自定义登录访问权限
		.authorizeRequests(authorizeRequests -> authorizeRequests
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated())
		// 自定义 userDetailsService
		.userDetailsService(tbUserInfo0aService)
		// 整合多种登录方式
//		.and()
		.apply(new LoginFilterSecurityConfigurer<>())
		.cusLogin(cusLoginConfigurer -> cusLoginConfigurer
				.cusUserDetailsService(cusUserInfo0aService)
//				.successHandler(loginAuthenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
		)
		;

		// ---------- //
		// 自定义登出配置
		// ---------- //
		http
		.logout()
		// 自定义登出页
		.logoutUrl("/logout")
		// 自定义登出成功处理
		.logoutSuccessHandler(((request, response, authentication) -> 
		{
			// 重定向至：/oauth2/authorize?
			String authorizeHost = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/oauth2/authorize?";
			String queryString = request.getHeader("referer").split("\\?")[1];
			String url = authorizeHost + queryString;
			response.sendRedirect(url);
        }))
		// 清除认证信息
        .clearAuthentication(true)
        .invalidateHttpSession(true)
		;
		return http.build();
	}

//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
//	{
//		// 默认登录/登出页
//		http
//		.formLogin(withDefaults())
//		.authorizeRequests(authorizeRequests -> authorizeRequests
//				.anyRequest().authenticated())
//		.userDetailsService(tbUserInfo0aService)
//		;
//		return http.build();
//	}
	
	// 仅用于【注册方式：数据库】
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return MyPasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
//	@Bean
//	UserDetailsService users()
//	{
//		UserDetails user = User.withDefaultPasswordEncoder().username("admin").password("password").roles("USER")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}
	
//	@Bean
//	UserDetailsService users()
//	{
//		UserDetails user = User.builder().username("admin").password("password")
//				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}
	
//	@Bean
//	UserDetailsService users()
//	{
//		UserDetails user = User.builder().username("admin").password("password")
//				.passwordEncoder(MyPasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}
	
//	private void sendAuthorizationResponse(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException {
//
//		OAuth2AuthorizationCodeRequestAuthenticationToken authorizationCodeRequestAuthentication =
//				(OAuth2AuthorizationCodeRequestAuthenticationToken) authentication;
//		UriComponentsBuilder uriBuilder = UriComponentsBuilder
//				.fromUriString(authorizationCodeRequestAuthentication.getRedirectUri())
//				.queryParam(OAuth2ParameterNames.CODE, authorizationCodeRequestAuthentication.getAuthorizationCode().getTokenValue());
//		if (StringUtils.hasText(authorizationCodeRequestAuthentication.getState())) {
//			uriBuilder.queryParam(OAuth2ParameterNames.STATE, authorizationCodeRequestAuthentication.getState());
//		}
//		this.redirectStrategy.sendRedirect(request, response, uriBuilder.toUriString());
//	}

//	private void sendErrorResponse(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException exception) throws IOException {
//
//		OAuth2AuthorizationCodeRequestAuthenticationException authorizationCodeRequestAuthenticationException =
//				(OAuth2AuthorizationCodeRequestAuthenticationException) exception;
//		OAuth2Error error = authorizationCodeRequestAuthenticationException.getError();
//		OAuth2AuthorizationCodeRequestAuthenticationToken authorizationCodeRequestAuthentication =
//				authorizationCodeRequestAuthenticationException.getAuthorizationCodeRequestAuthentication();
//
//		if (authorizationCodeRequestAuthentication == null ||
//				!StringUtils.hasText(authorizationCodeRequestAuthentication.getRedirectUri())) {
//			// TODO Send default html error response
//			response.sendError(HttpStatus.BAD_REQUEST.value(), error.toString());
//			return;
//		}
//
//		UriComponentsBuilder uriBuilder = UriComponentsBuilder
//				.fromUriString(authorizationCodeRequestAuthentication.getRedirectUri())
//				.queryParam(OAuth2ParameterNames.ERROR, error.getErrorCode());
//		if (StringUtils.hasText(error.getDescription())) {
//			uriBuilder.queryParam(OAuth2ParameterNames.ERROR_DESCRIPTION, error.getDescription());
//		}
//		if (StringUtils.hasText(error.getUri())) {
//			uriBuilder.queryParam(OAuth2ParameterNames.ERROR_URI, error.getUri());
//		}
//		if (StringUtils.hasText(authorizationCodeRequestAuthentication.getState())) {
//			uriBuilder.queryParam(OAuth2ParameterNames.STATE, authorizationCodeRequestAuthentication.getState());
//		}
//		this.redirectStrategy.sendRedirect(request, response, uriBuilder.toUriString());
//	}
}
