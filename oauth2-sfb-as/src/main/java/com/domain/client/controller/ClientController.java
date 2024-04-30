package com.domain.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/client")
public class ClientController
{
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Model model, HttpServletRequest request)
	{
	    if (request.getAttribute("error") != null) {
	    	model.addAttribute("error", request.getAttribute("error"));
	    	model.addAttribute("errorMessage", request.getAttribute("errorMessage"));
	    }
	    if (request.getParameter("error") != null) {
	    	model.addAttribute("error", request.getParameter("error"));
	    	model.addAttribute("errorMessage", request.getParameter("errorMessage"));
	    }
		return "client/login";
	}
}
