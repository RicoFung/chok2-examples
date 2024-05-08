package com.domain.client.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/client")
public class ClientController
{
    @Value("${oauth2.client.login-page}")
    private String LOGIN_PAGE;
    
	/**
	 * 指向自定义登录页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpServletRequest request)
	{
		// 回写当前语言
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale currentLocale = localeResolver.resolveLocale(request);
		model.addAttribute("currentLang", currentLocale.toLanguageTag().replace('-', '_'));
		// 指向  templates/client/login.html
		return LOGIN_PAGE;
	}
}
