package com.config;

import java.net.URI;
import java.net.URISyntaxException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//@Component
public class AuthPreProcessingFilter //extends OncePerRequestFilter
{
//	private final Logger logger = LoggerFactory.getLogger(getClass());
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws java.io.IOException, ServletException {
//		if (request.getRequestURI().contains("/oauth2/authorize"))
//		{
//			// 获取 client_id
//			String clientId = request.getParameter("client_id");
//			logger.info("==> clientId: {}", clientId);
//			if (clientId != null)
//			{
//				HttpSession session = request.getSession(true);
//				session.setAttribute("clientId", clientId);
//			}
//			// 获取 redirect_uri
//			String redirectUri = request.getParameter("redirect_uri");
//			logger.info("==> redirectUri: {}", redirectUri);
//			if (redirectUri != null)
//			{
//				try
//				{
//					URI uri = new URI(redirectUri);
//					String domain = uri.getScheme() + "://" + uri.getHost() + (uri.getPort() != -1 ? ":" + uri.getPort() : "");
//					HttpSession session = request.getSession(true);
//					session.setAttribute("refererDomain", domain);
//					logger.info("<== refererDomain: {}", domain);
//				}
//				catch (URISyntaxException e)
//				{
//					logger.error("<== Exception: {}", e.getMessage());
//				}
//			}
//		}
//		filterChain.doFilter(request, response);
//	}
}