package com.domain.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/i18n")
public class i18nController
{

	private final LocaleResolver localeResolver;

	public i18nController(LocaleResolver localeResolver)
	{
		this.localeResolver = localeResolver;
	}

	@GetMapping("/change")
	public String change(@RequestParam("lang") String lang, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes)
	{
		if (localeResolver == null)
		{
			throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
		}
		Locale newLocale = Locale.forLanguageTag(lang.replace('_', '-'));
		localeResolver.setLocale(request, response, newLocale);

//	    String referer = request.getHeader("Referer");
//	    String redirectUrl = "/";
//		if (referer != null && referer.startsWith("http://auth-server/"))
//		{
//			redirectUrl = UriComponentsBuilder.fromUriString(referer).replaceQuery(null).build().toUriString();
//		}
//	    return "redirect:" + (redirectUrl != null ? redirectUrl : "/");
	    
		String referer = request.getHeader("Referer");
		return "redirect:" + (referer != null ? referer : "/");
	}
}
