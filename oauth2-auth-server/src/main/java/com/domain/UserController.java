package com.domain;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
	@RequestMapping(value = "/userinfo", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getUserInfo(Principal principal)
	{
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("name", principal.getName());
		userInfo.put("principal", principal);
		return userInfo;
	}
	
//	public Authentication oauth2Userinfo()
//	{
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null)
//		{
//			throw new RuntimeException("这个地方想办法处理401");
//		}
//		return authentication;
//	}
}
