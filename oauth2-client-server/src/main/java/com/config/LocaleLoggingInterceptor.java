package com.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Component
public class LocaleLoggingInterceptor implements HandlerInterceptor
{

	private static final Logger logger = LoggerFactory.getLogger(LocaleLoggingInterceptor.class);

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	{
		Locale locale = localeResolver.resolveLocale(request);
		logger.info("Current Locale: {}", locale);
		return true;
	}
}
