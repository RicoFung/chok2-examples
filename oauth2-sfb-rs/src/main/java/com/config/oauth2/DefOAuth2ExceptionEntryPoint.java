package com.config.oauth2;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import chok.common.RestConstants;
import chok.devwork.pojo.ChokDto;

public class DefOAuth2ExceptionEntryPoint implements AuthenticationEntryPoint
{
	private static Logger log = LoggerFactory.getLogger(DefOAuth2ExceptionEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		// 组装数据
		ChokDto<Object> dto = new ChokDto<Object>();
		dto.setSuccess(false);
		dto.setCode(RestConstants.ERROR_CODE2);
		dto.setMsg(authException.getMessage());
//		dto.setData(authException.getCause());
		dto.setPath(request.getServletPath());
		dto.setTimestamp(String.valueOf(new Date().getTime()));
		// 返回数据
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		try
		{
			objectMapper.writeValue(response.getOutputStream(), dto);
		}
		catch (Exception e)
		{
			log.error(objectMapper.writeValueAsString(e));
		}
		finally
		{
			log.error(objectMapper.writeValueAsString(dto));
		}
	}
}
