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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.LocaleResolver;

import com.domain.auth.service.TbUserInfo0aService;

@EnableWebSecurity(debug = false)
public class AuthSecurityConfig
{
    @Value("${auth.login-page}")
    private String authLoginPage;
    @Value("${auth.login-processing-url}")
    private String authLoginProcessingUrl;
    
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;
	@Autowired
	private AuthProcessingFilter	authProcessingFilter;
	@Autowired
	private AuthPreProcessingFilter	authPreProcessingFilter;
	@Autowired
	TbUserInfo0aService		tbUserInfo0aService;
	
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
	public AuthProcessingFailureHandler failureHandler(MessageSource messageSource, LocaleResolver localeResolver)
	{
		return new AuthProcessingFailureHandler(messageSource, localeResolver);
	}
    
	@Bean("authPreProcessingSecurityFilterChain")
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authPreProcessingSecurityFilterChain(HttpSecurity http) throws Exception
	{
		// 默认配置
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		// **************************************************************************************** 
		// 此拦截器用于获取/oauth2/authorize的相关请求参数
		// **************************************************************************************** 
		http.addFilterBefore(authPreProcessingFilter, BasicAuthenticationFilter.class);
		http.formLogin().loginPage(authLoginPage);
		return http.build();
	}
	
	@Bean("authProcessingSecurityFilterChain")
	@Order(Ordered.HIGHEST_PRECEDENCE + 1)
	SecurityFilterChain authProcessingSecurityFilterChain(HttpSecurity http, @Qualifier("authPreProcessingSecurityFilterChain") SecurityFilterChain securityFilterChain) throws Exception
	{
		DefaultSecurityFilterChain oauth2SecurityFilterChain = (DefaultSecurityFilterChain) securityFilterChain;

		// ---------- //
		// 自定义登录配置
		// ---------- //
		http
		// **************************************************************************************** 
		// 这段代码的作用是为了创建一个请求匹配器，该匹配器用于识别那些不应由授权服务器处理的请求。
		// 通过 new NegatedRequestMatcher(oauth2SecurityFilterChain.getRequestMatcher())，
		// 它否定了授权服务器的请求匹配器，意味着如果请求不符合授权服务器的路径，就会匹配此安全配置。
		// 这允许自定义登录和其他安全逻辑独立于授权服务器流程进行处理。
		// **************************************************************************************** 
		.requestMatcher(new AndRequestMatcher(new NegatedRequestMatcher(oauth2SecurityFilterChain.getRequestMatcher())))
		.csrf().disable()
		.addFilterBefore(authProcessingFilter, UsernamePasswordAuthenticationFilter.class)
		.formLogin()
		// 自定义登录页
		.loginPage(authLoginPage)
		// 自定义登录页拦截路径
		.loginProcessingUrl(authLoginProcessingUrl)
		// 自定义登录失败拦截路径
		.failureHandler(failureHandler(messageSource, localeResolver))
		.and()
		// 放开自定义登录访问权限
		.authorizeRequests(authorizeRequests -> authorizeRequests
				.antMatchers(
						// 【注意】以下静态资源必须写两种过滤表达式，否则引入静态资源失败
						"**/jquery-easyui/**",
						"/jquery-easyui/**",
						// 【注意】以下静态资源必须写两种过滤表达式，否则引入静态资源失败
						"**/default/*.js",
						"/default/*.js",
						"**/client/*.js",
						"/client/*.js",
						// 国际化切换
						"/i18n/change",
						// 客户端登录
						authLoginPage,
						"/default/login",
						"/client/login"
						).permitAll()
				.anyRequest().authenticated())
		// 自定义 userDetailsService
		.userDetailsService(tbUserInfo0aService)
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
			String queryString = request.getQueryString();
			String url = authorizeHost + queryString;
			response.sendRedirect(url);
        }))
		// 清除认证信息
        .clearAuthentication(true)
        .invalidateHttpSession(true)
		;
		return http.build();
	}

	// 仅用于【注册方式：数据库】
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return AuthProcessingFactory.createDelegatingPasswordEncoder();
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
//				.passwordEncoder(AuthProcessingFactory.createDelegatingPasswordEncoder()::encode).roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}
	
}
