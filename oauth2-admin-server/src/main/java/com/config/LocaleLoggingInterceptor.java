package com.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

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
		if (logger.isDebugEnabled()) logger.debug("Current Locale: {}", locale);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
	{
		if (modelAndView != null)
		{
			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
			Locale currentLocale = localeResolver.resolveLocale(request);
			modelAndView.addObject("currentLang", currentLocale.toLanguageTag().replace('-', '_'));
			if (logger.isDebugEnabled()) logger.debug("currentLang ==> lang: {}", modelAndView.getModel().get("currentLang"));
		}
	}
}
