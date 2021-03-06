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


import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.domain.erp.service.TbUserInfo0aService;

@EnableWebSecurity(debug = true)
public class DefaultSecurityConfig
{
	@Autowired
	TbUserInfo0aService		tbUserInfo0aService;
//	@Autowired
//	CusUserInfo0aService	cusUserInfo0aService;
	
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
//	@Order(Ordered.HIGHEST_PRECEDENCE)
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
	{
		// 默认登录/登出页
//		http
//		.formLogin(withDefaults())
//		.authorizeRequests(authorizeRequests -> authorizeRequests
//				.anyRequest().authenticated())
//		.userDetailsService(tbUserInfo0aService)
//		;
		
		// 自定义登录页
		http
		.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login"))
		.authorizeRequests(authorizeRequests -> authorizeRequests
				// 放开自定义登录访问权限
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated())
		.userDetailsService(tbUserInfo0aService)
		;

		// 自定义登出
		http
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessHandler(((request, response, authentication) -> 
		{
			// 重定向至：/oauth2/authorize?
			String authorizeHost = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/oauth2/authorize?";
			String queryString = request.getHeader("referer").split("\\?")[1];
			String url = authorizeHost + queryString;
			response.sendRedirect(url);
        }))
		//清除认证信息
        .clearAuthentication(true)
        .invalidateHttpSession(true)
		;
		return http.build();
	}

	// @Bean
	// UserDetailsService users() {
	// UserDetails user = User.withDefaultPasswordEncoder()
	// .username("admin")
	// .password("password")
	// .roles("USER")
	// .build();
	// return new InMemoryUserDetailsManager(user);
	// }
	
	
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
	
	// 仅用于【注册方式：数据库】
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return MyPasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
