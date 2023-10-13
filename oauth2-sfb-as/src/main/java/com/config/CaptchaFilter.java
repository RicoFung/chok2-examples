package com.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CaptchaFilter extends OncePerRequestFilter
{
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		if ("/customize/loginProcess".equals(request.getRequestURI()))
		{
			// 从 Session 获取正确的验证码
			String sessionCaptcha = "888888";
			// 从 Form 获取提交的验证码
			String userInputCaptcha = request.getParameter("captcha");
			// 校验
			if (sessionCaptcha == null || !sessionCaptcha.equals(userInputCaptcha))
			{
				handleCaptchaFailure(request, response);
				return;
			}
		}
		// 验证通过，进入下一个过滤器
		filterChain.doFilter(request, response);
	}
	
	private void handleCaptchaFailure(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
		request.setAttribute("captchaError", "Captcha mismatched!");
		request.getRequestDispatcher("/customize/login").forward(request, response);
	}
}
