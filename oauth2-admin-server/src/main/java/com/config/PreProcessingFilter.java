package com.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.LocaleResolver;

@Component
public class PreProcessingFilter extends OncePerRequestFilter
{
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		String redirectedFrom = request.getParameter("redirectFrom");
		if ("/i18nChange".equals(redirectedFrom)) 
		{
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		if ("/login/process".equals(request.getRequestURI()))
		{
//			// 从 Session 获取正确的验证码
//			String sessionCaptcha = "888888";
//			// 从 Form 获取提交的验证码
//			String userInputCaptcha = request.getParameter("captcha");
//			// 校验
//			if (sessionCaptcha == null || !sessionCaptcha.equals(userInputCaptcha))
//			{
//				handleCaptchaFailure(request, response);
//				return;
//			}
		}
		// 验证通过，进入下一个过滤器
		filterChain.doFilter(request, response);
	}
	
	/**
	 * 处理验证码错误
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@SuppressWarnings("unused")
	private void handleCaptchaFailure(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		// 读取对应的 messageSource
		String errorMessage = messageSource.getMessage("login.error.message.captcha", null, localeResolver.resolveLocale(request));
		// ClientController.java 通过 request.getAttribute 读取
		request.setAttribute("error", true);
		request.setAttribute("errorMessage", errorMessage);
		request.getRequestDispatcher("/login").forward(request, response);
	}
}
