package com.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class ClientAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException
	{
		String errorMessage = "Login Fail.";

		if (exception instanceof UsernameNotFoundException)
		{
			errorMessage = "No user found with the username provided.";
		}
		if (exception instanceof BadCredentialsException)
		{
			errorMessage = "Invalid username or password.";
		}
		// 方式一：
		// ClientController.java 通过 request.getParameter 读取
		setDefaultFailureUrl("/client/login?error=true&errorMessage=" + errorMessage);
		super.onAuthenticationFailure(request, response, exception);
		// 方式二：
		// ClientController.java 通过 request.getAttribute 读取
//		request.setAttribute("error", true);
//		request.setAttribute("errorMessage", errorMessage);
//		request.getRequestDispatcher("/client/login").forward(request, response);
	}
}
