package com.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.servlet.LocaleResolver;

public class AuthLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
    @Value("${oauth2.client.login-page}")
    private String LOGIN_PAGE;
    
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

	public AuthLoginFailureHandler(MessageSource messageSource, LocaleResolver localeResolver)
	{
		this.messageSource = messageSource;
		this.localeResolver = localeResolver;
	}
    
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException
	{
		String errorMessage = messageSource.getMessage("login.error.message.default", null, localeResolver.resolveLocale(request));
		if (exception instanceof UsernameNotFoundException)
		{
			errorMessage = messageSource.getMessage("login.error.message.nouserfound", null, localeResolver.resolveLocale(request));
		}
		if (exception instanceof BadCredentialsException)
		{
			errorMessage = messageSource.getMessage("login.error.message.badcredentials", null, localeResolver.resolveLocale(request));
		}
		// ClientController.java 通过 request.getAttribute 读取
		request.setAttribute("error", true);
		request.setAttribute("errorMessage", errorMessage);
		request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
	}
}
