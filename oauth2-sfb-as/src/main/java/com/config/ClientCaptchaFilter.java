package com.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class ClientCaptchaFilter extends OncePerRequestFilter
{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		if ("/client/loginProcess".equals(request.getRequestURI()))
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
	
	/**
	 * 处理验证码错误
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void handleCaptchaFailure(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		// 方式一：
		// ClientController.java 通过 request.getParameter 读取
		String errorMessage = "Captcha mismatched!";
		this.redirectStrategy.sendRedirect(request, response, "/client/login?error=true&errorMessage=" + errorMessage);
		// 方式二：
		// ClientController.java 通过 request.getAttribute 读取
//		request.setAttribute("error", true);
//		request.setAttribute("errorMessage", "Captcha mismatched!");
//		request.getRequestDispatcher("/client/login").forward(request, response);
	}
}
