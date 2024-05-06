package com.domain.client.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpServletRequest request)
	{
		// 回写当前语言
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale currentLocale = localeResolver.resolveLocale(request);
		model.addAttribute("currentLang", currentLocale.toLanguageTag().replace('-', '_'));
		// 回写错误提示
		if (request.getAttribute("error") != null)
		{
			model.addAttribute("error", request.getAttribute("error"));
			model.addAttribute("errorMessage", request.getAttribute("errorMessage"));
		}
		if (request.getParameter("error") != null)
		{
			model.addAttribute("error", request.getParameter("error"));
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}
		return "client/login";
	}
}
