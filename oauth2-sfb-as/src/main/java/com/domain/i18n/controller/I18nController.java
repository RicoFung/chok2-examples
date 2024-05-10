package com.domain.i18n.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/i18n")
public class I18nController
{
	private static final Logger logger = LoggerFactory.getLogger(I18nController.class);
	
	private final LocaleResolver localeResolver;

	public I18nController(LocaleResolver localeResolver)
	{
		this.localeResolver = localeResolver;
	}

	/**
	 * 切换语言
	 * @param lang
	 * @param request
	 * @param response
	 * @return
	 */
	// 【用于模拟异常】： @RequestMapping(value = "/change", method = { RequestMethod.POST })
	@GetMapping("/change")
	public String change(@RequestParam("lang") String lang, HttpServletRequest request, HttpServletResponse response)
	{
		logger.info("RequestParam ==> lang: {}", lang);
		Locale newLocale = Locale.forLanguageTag(lang.replace('_', '-'));
		localeResolver.setLocale(request, response, newLocale);
	
		String redirectUrl = "/";
		String referer = request.getHeader("Referer");
		logger.info("Header ==> referer: {}", referer);
		if (referer != null)
		{
			// 使用 UriComponentsBuilder 
			// 使用 replaceQueryParam() 移除上次的  error、errorMessage、redirectFrom 参数
			// 使用 queryParam() 添加最新的 redirectFrom 参数
			redirectUrl = UriComponentsBuilder
					.fromUriString(referer)
					.replaceQueryParam("error")
					.replaceQueryParam("errorMessage")
					.replaceQueryParam("redirectFrom")
					.queryParam("redirectFrom", "/i18nChange")
					.build().toUriString();
		}
		logger.info("Redirect <== url: {}", referer);
		return "redirect:" + redirectUrl;
	}
}
