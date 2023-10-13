package com.domain.customize.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customize")
public class CustomizeController
{
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Model model, HttpServletRequest request)
	{
	    if (request.getAttribute("captchaError") != null) {
	        model.addAttribute("captchaError", request.getAttribute("captchaError"));
	    }
		return "customize/login";
	}
}
