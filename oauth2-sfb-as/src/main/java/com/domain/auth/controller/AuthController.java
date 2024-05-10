package com.domain.auth.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/auth")
public class AuthController
{
	/**
	 * 跳转自定义登录页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/forward", method = { RequestMethod.GET, RequestMethod.POST })
	public String forward(Model model, HttpServletRequest request)
	{
		// 回写当前语言
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale currentLocale = localeResolver.resolveLocale(request);
		model.addAttribute("currentLang", currentLocale.toLanguageTag().replace('-', '_'));
		// 从会话中获取 clientId
		HttpSession session = request.getSession(false);
	    String clientId = session != null ? (String) session.getAttribute("clientId") : null;
	    // 按 clientId 跳转登录页
	    if ("rico-client".equals(clientId))
	    {
	    	return "/client/login";
	    }
	    else
	    {
	    	return "/default/login";
	    }
	}
}
