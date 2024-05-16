package com.config;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class i18nConfig
{
	private static final Logger logger = LoggerFactory.getLogger(i18nConfig.class);

	/** 国际化文件路径 */
	@Value("${spring.messages.basename}")
	public String	basename;
	@Value("${spring.messages.encoding}")
	public String	encoding;

	/**
	 * 用于解析消息的策略接口，支持这些消息的参数化和国际化。
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource()
	{
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(basename);
		messageSource.setDefaultEncoding(encoding);
		logger.info("Configuring MessageSource with basename: {}, encoding: {}", basename, encoding);
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver()
	{
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	    sessionLocaleResolver.setDefaultLocale(Locale.getDefault()); // 设置默认语言为系统默认语言
		return sessionLocaleResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor()
	{
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang"); // 用于更改语言的请求参数名
		return interceptor;
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer(LocaleChangeInterceptor localeChangeInterceptor, LocaleLoggingInterceptor localeLoggingInterceptor)
	{
		return new WebMvcConfigurer()
		{
			@Override
			public void addInterceptors(InterceptorRegistry registry)
			{
				registry.addInterceptor(localeChangeInterceptor);
	            registry.addInterceptor(localeLoggingInterceptor); // 添加日志拦截器
			}
		};
	}
}
