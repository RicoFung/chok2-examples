package com.domain.customize;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Oauth2Controller
{
	
	@GetMapping("login")
	public String login()
	{
		return "login";
	}
	
//	@GetMapping("loginCustom")
//	public String loginCustom()
//	{
//		return "loginCustom";
//	}
}
