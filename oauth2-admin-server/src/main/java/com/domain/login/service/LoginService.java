package com.domain.login.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService
{
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		// 这里你可以根据你的需求从数据库或其他地方加载用户信息
		if ("admin".equals(username))
		{
			return User.withUsername(username).password("{noop}123") // {noop} 表示不使用密码加密，实际项目中应使用加密密码
					.roles("ADMIN").build();
		}
		else
		{
			throw new UsernameNotFoundException("User not found");
		}
	}
}
