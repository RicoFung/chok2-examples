package com.config.oauth2;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import chok.common.RestConstants;
import chok.devwork.pojo.ChokDto;

public class DefAccessDeniedHandler implements AccessDeniedHandler
{
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		// 组装数据
		ChokDto<Object> dto = new ChokDto<Object>();
		dto.setSuccess(false);
		dto.setCode(RestConstants.ERROR_CODE3);
		dto.setMsg(accessDeniedException.getMessage());
//		dto.setData(accessDeniedException.getCause() == null ? accessDeniedException.getMessage() : accessDeniedException.getCause());
		dto.setPath(request.getServletPath());
		dto.setTimestamp(String.valueOf(new Date().getTime()));
		// 返回数据
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
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
