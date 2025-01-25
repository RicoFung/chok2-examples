package com.config;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.LocaleResolver;

//@Component
public class AuthProcessingFilter //extends OncePerRequestFilter
{
//    @Value("${auth.login-page}")
//    private String authLoginPage;
//    @Value("${auth.login-processing-url}")
//    private String authLoginProcessingUrl;
//
//    @Autowired
//    private MessageSource messageSource;
//    @Autowired
//    private LocaleResolver localeResolver;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException
//	{
//		String redirectedFrom = request.getParameter("redirectFrom");
//		if ("/i18nChange".equals(redirectedFrom))
//		{
//			request.getRequestDispatcher(authLoginPage).forward(request, response);
//			return;
//		}
//		if (authLoginProcessingUrl.equals(request.getRequestURI()))
//		{
//			// 从 Session 中获取 clientId
//			HttpSession session = request.getSession(false);
//		    String clientId = session != null ? (String) session.getAttribute("clientId") : null;
//		    // 按 clientId 执行处理逻辑
//		    if ("rico-client".equals(clientId))
//		    {
//				// 从 Session 获取正确的验证码
//				String sessionCaptcha = "888888";
//				// 从 Form 获取提交的验证码
//				String userInputCaptcha = request.getParameter("captcha");
//				// 校验
//				if (sessionCaptcha == null || !sessionCaptcha.equals(userInputCaptcha))
//				{
//					handleCaptchaFailure(request, response);
//					return;
//				}
//		    }
//		    else
//		    {
//
//		    }
//		}
//		// 验证通过，进入下一个过滤器
//		filterChain.doFilter(request, response);
//	}
//
//	/**
//	 * 处理验证码错误
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 * @throws ServletException
//	 */
//	private void handleCaptchaFailure(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
//	{
//		// 读取对应的 messageSource
//		String errorMessage = messageSource.getMessage("login.error.message.captcha", null, localeResolver.resolveLocale(request));
//		// ClientController.java 通过 request.getAttribute 读取
//		request.setAttribute("error", true);
//		request.setAttribute("errorMessage", errorMessage);
//		request.getRequestDispatcher(authLoginPage).forward(request, response);
//	}
}
