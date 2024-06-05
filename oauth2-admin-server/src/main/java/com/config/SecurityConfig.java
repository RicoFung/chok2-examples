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
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.LocaleResolver;

import com.domain.login.service.LoginService;
import com.filter.PreProcessingFilter;
import com.handler.LoginFailureHandler;
import com.handler.LoginSuccessHandler;

@EnableWebSecurity(debug = false)
public class SecurityConfig
{
	private MessageSource		messageSource;
	@Autowired
	private LocaleResolver		localeResolver;
	@Autowired
	private PreProcessingFilter	preProcessingFilter;
	@Autowired
	private LoginService		loginService;

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
    public LoginSuccessHandler loginSuccessHandler()
    {
    	return new LoginSuccessHandler();
    }
    
    @Bean
    public LoginFailureHandler loginFailureHandler(MessageSource messageSource, LocaleResolver localeResolver)
    {
    	return new LoginFailureHandler(messageSource, localeResolver);
    }

    @Bean("adminSecurityFilterChain")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception 
    {
        http
        .requestMatcher(new AntPathRequestMatcher("/**"))
        .csrf().disable()
		.addFilterBefore(preProcessingFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login/process")
        .successHandler(loginSuccessHandler())
        .failureHandler(loginFailureHandler(messageSource, localeResolver))
        .and()
        .authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers(
						// 【注意】以下静态资源必须写两种过滤表达式，否则引入静态资源失败
						"**/jquery-easyui/**",
						"/jquery-easyui/**",
						"/*.js",
						// 国际化切换
						"/i18n/change",
						// 管理端登录
						"/login/**"
                		).permitAll()
                .anyRequest().authenticated())
        .userDetailsService(loginService);

        http.logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(((request, response, authentication) -> {
            response.sendRedirect("/login");
        }))
        .clearAuthentication(true)
        .invalidateHttpSession(true);
        
        return http.build();
    }
    
	
}
