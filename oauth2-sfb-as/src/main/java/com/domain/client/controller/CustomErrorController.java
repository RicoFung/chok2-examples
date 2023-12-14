package com.domain.client.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController
{

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request)
	{
		// Get the status code to display the appropriate error message
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null)
		{
			int statusCode = Integer.valueOf(status.toString());
			// Handle the specific status code, log, or display a custom error page
		}
		return "error";
	}
	
}
