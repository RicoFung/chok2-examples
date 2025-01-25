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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.LocaleResolver;

import com.domain.auth.service.TbUserInfo0aService;

//@EnableWebSecurity(debug = true)
public class AuthSecurityConfig
{
//    @Value("${auth.login-page}")
//    private String authLoginPage;
//    @Value("${auth.login-processing-url}")
//    private String authLoginProcessingUrl;
//
//    @Autowired
//    private MessageSource messageSource;
//    @Autowired
//    private LocaleResolver localeResolver;
//	@Autowired
//	private AuthProcessingFilter	authProcessingFilter;
//	@Autowired
//	private AuthPreProcessingFilter	authPreProcessingFilter;
//	@Autowired
//	TbUserInfo0aService		tbUserInfo0aService;
//
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource()
//	{
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
//		configuration.setAllowCredentials(true);
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration); // 对所有url生效
//		return source;
//	}
//	@Bean
//	@Order(1) // 确保此过滤链优先处理
//	public SecurityFilterChain authPreProcessingSecurityFilterChain(HttpSecurity http) throws Exception {
//		// 配置 OAuth2 Authorization Server
//		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
//		http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher()) // 匹配授权服务器端点
//				.authorizeHttpRequests(authorize -> authorize
//						.anyRequest().authenticated() // 所有请求需要认证
//				)
//				.csrf(csrf -> csrf
//						.ignoringRequestMatchers(authorizationServerConfigurer.getEndpointsMatcher()) // 忽略 CSRF 对授权端点的保护
//				);
//
//		// 配置自定义拦截器
//		http.addFilterBefore(authPreProcessingFilter, BasicAuthenticationFilter.class);
//
//		// 使用 DSL 风格配置 OAuth2AuthorizationServerConfigurer
//		http.apply(authorizationServerConfigurer);
//
//		// 配置表单登录
//		http.formLogin(form -> form
//				.loginPage(authLoginPage) // 自定义登录页面路径
//				.permitAll() // 允许所有用户访问登录页面
//		);
//
//		return http.build();
//	}
//
//	@Bean
//	@Order(2) // 确保此过滤链优先级低于授权服务器链
//	public SecurityFilterChain authProcessingSecurityFilterChain(HttpSecurity http) throws Exception {
//		// 匹配非授权服务器路径
//		RequestMatcher nonOAuth2RequestMatcher = new NegatedRequestMatcher(
//				new AntPathRequestMatcher("/oauth2/**")
//		);
//
//		http.securityMatcher(nonOAuth2RequestMatcher)
//				.csrf(csrf -> csrf.disable()) // 禁用 CSRF
//				.addFilterBefore(authProcessingFilter, UsernamePasswordAuthenticationFilter.class)
//				.formLogin(form -> form
//						.loginPage(authLoginPage) // 自定义登录页面路径
//						.loginProcessingUrl(authLoginProcessingUrl) // 登录处理路径
//						.failureHandler(new AuthProcessingFailureHandler(messageSource, localeResolver)) // 自定义登录失败处理
//						.permitAll() // 登录页面允许所有用户访问
//				)
//				.authorizeHttpRequests(authorize -> authorize
//						.requestMatchers(
//								"/jquery-easyui/**", // 静态资源
//								"/default/*.js",
//								"/client/*.js",
//								"/i18n/change",
//								authLoginPage,
//								"/default/login",
//								"/client/login"
//						).permitAll() // 放开静态资源和登录页面的访问权限
//						.anyRequest().authenticated() // 其他请求需要认证
//				)
//				.userDetailsService(tbUserInfo0aService); // 自定义用户服务
//
//		// 配置登出
//		http.logout(logout -> logout
//				.logoutUrl("/logout") // 自定义登出路径
//				.logoutSuccessHandler((request, response, authentication) -> {
//					// 重定向到授权页面
//					String authorizeUrl = String.format("%s://%s:%d/oauth2/authorize?",
//							request.getScheme(), request.getServerName(), request.getServerPort());
//					response.sendRedirect(authorizeUrl + request.getQueryString());
//				})
//				.clearAuthentication(true) // 清除认证信息
//				.invalidateHttpSession(true) // 清除 Session
//		);
//
//		return http.build();
//	}

//	@Bean
//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
//		// 配置 CORS
//		http.cors(cors -> cors.configurationSource(corsConfigurationSource));
//		// 配置 CSRF
//		http.csrf(csrf -> csrf.ignoringRequestMatchers("/oauth2/**"));
//
//		// 配置自定义拦截器
//		http.addFilterBefore(authPreProcessingFilter, BasicAuthenticationFilter.class);
//		http.addFilterBefore(authProcessingFilter, UsernamePasswordAuthenticationFilter.class);
//
//		// 配置授权服务器端点
//		http.securityMatcher("/oauth2/**")
//				.authorizeHttpRequests(authorize -> authorize
//						.requestMatchers("/oauth2/**").authenticated() // 拦截 /oauth2/** 并要求认证
//						.anyRequest().permitAll() // 其他请求允许访问
//				);
//
//		// 配置表单登录
//		http.formLogin(form -> form
//				.loginPage(authLoginPage) // 自定义登录页面
//				.loginProcessingUrl(authLoginProcessingUrl) // 登录处理路径
//				.failureHandler(new AuthProcessingFailureHandler(messageSource, localeResolver)) // 登录失败处理
//				.permitAll() // 登录页面允许所有用户访问
//		);
//
//		// 配置登出
//		http.logout(logout -> logout
//				.logoutUrl("/logout")
//				.logoutSuccessHandler((request, response, authentication) -> {
//					String authorizeHost = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/oauth2/authorize";
//					response.sendRedirect(authorizeHost);
//				})
//				.clearAuthentication(true)
//				.invalidateHttpSession(true)
//		);
//
//		// 配置 UserDetailsService
//		http.userDetailsService(tbUserInfo0aService);
//
//		return http.build();
//	}

//    @Bean
//	public AuthProcessingFailureHandler failureHandler(MessageSource messageSource, LocaleResolver localeResolver)
//	{
//		return new AuthProcessingFailureHandler(messageSource, localeResolver);
//	}

//	public SecurityFilterChain authPreProcessingSecurityFilterChain(HttpSecurity http) throws Exception
//	{
//		// 默认配置
//		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//		// ****************************************************************************************
//		// 此拦截器用于获取/oauth2/authorize的相关请求参数
//		// ****************************************************************************************
//		http.addFilterBefore(authPreProcessingFilter, BasicAuthenticationFilter.class);
//		http.formLogin(form -> form.loginPage(authLoginPage).permitAll());
//		return http.build();
//	}

//	SecurityFilterChain authProcessingSecurityFilterChain(HttpSecurity http, @Qualifier("authPreProcessingSecurityFilterChain") SecurityFilterChain securityFilterChain) throws Exception
//	{
//		DefaultSecurityFilterChain oauth2SecurityFilterChain = (DefaultSecurityFilterChain) securityFilterChain;
//
//		// ---------- //
//		// 自定义登录配置
//		// ---------- //
//		http
//		// ****************************************************************************************
//		// 这段代码的作用是为了创建一个请求匹配器，该匹配器用于识别那些不应由授权服务器处理的请求。
//		// 通过 new NegatedRequestMatcher(oauth2SecurityFilterChain.getRequestMatcher())，
//		// 它否定了授权服务器的请求匹配器，意味着如果请求不符合授权服务器的路径，就会匹配此安全配置。
//		// 这允许自定义登录和其他安全逻辑独立于授权服务器流程进行处理。
//		// ****************************************************************************************
//		.requestMatcher(new AndRequestMatcher(new NegatedRequestMatcher(oauth2SecurityFilterChain.getRequestMatcher())))
//		.csrf().disable()
//		.addFilterBefore(authProcessingFilter, UsernamePasswordAuthenticationFilter.class)
//		.formLogin()
//		// 自定义登录页
//		.loginPage(authLoginPage)
//		// 自定义登录页拦截路径
//		.loginProcessingUrl(authLoginProcessingUrl)
//		// 自定义登录失败拦截路径
//		.failureHandler(failureHandler(messageSource, localeResolver))
//		.and()
//		// 放开自定义登录访问权限
//		.authorizeRequests(authorizeRequests -> authorizeRequests
//				.antMatchers(
//						// 【注意】以下静态资源必须写两种过滤表达式，否则引入静态资源失败
//						"**/jquery-easyui/**",
//						"/jquery-easyui/**",
//						// 【注意】以下静态资源必须写两种过滤表达式，否则引入静态资源失败
//						"**/default/*.js",
//						"/default/*.js",
//						"**/client/*.js",
//						"/client/*.js",
//						// 国际化切换
//						"/i18n/change",
//						// 客户端登录
//						authLoginPage,
//						"/default/login",
//						"/client/login"
//						).permitAll()
//				.anyRequest().authenticated())
//		// 自定义 userDetailsService
//		.userDetailsService(tbUserInfo0aService)
//		;
//
//		// ---------- //
//		// 自定义登出配置
//		// ---------- //
//		http
//		.logout()
//		// 自定义登出页
//		.logoutUrl("/logout")
//		// 自定义登出成功处理
//		.logoutSuccessHandler(((request, response, authentication) ->
//		{
//			// 重定向至：/oauth2/authorize?
//			String authorizeHost = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/oauth2/authorize?";
//			String queryString = request.getQueryString();
//			String url = authorizeHost + queryString;
//			response.sendRedirect(url);
//        }))
//		// 清除认证信息
//        .clearAuthentication(true)
//        .invalidateHttpSession(true)
//		;
//		return http.build();
//	}

	// 仅用于【注册方式：数据库】
//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//		return AuthProcessingFactory.createDelegatingPasswordEncoder();
//	}
	
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
