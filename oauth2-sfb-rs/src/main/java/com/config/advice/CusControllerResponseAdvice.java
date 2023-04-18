package com.config.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import chok2.devwork.pojo.ChokDto;

/*******************************************
 * 
 * 只能拦截到Controller层的异常，且Controller自行try catch的话会无效
 * 
 * @author rico.fung
 *
 */
@RestControllerAdvice(basePackages = { "com.api.v3.tbdemo.controller" })
public class CusControllerResponseAdvice implements ResponseBodyAdvice<Object>
{
	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass)
	{
		// response是ChokDto类型，或者注释了NotControllerResponseAdvice都不进行包装
		return !methodParameter.getParameterType().isAssignableFrom(ChokDto.class);
	}

	@Override
	public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType,
			Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response)
	{
		// String类型不能直接包装
		if (returnType.getGenericParameterType().equals(String.class))
		{
			ObjectMapper objectMapper = new ObjectMapper();
			try
			{
				// 将数据包装在ResultVo里后转换为json串进行返回
				return objectMapper.writeValueAsString(new ChokDto<Object>(data));
			}
			catch (JsonProcessingException e)
			{
				throw new RuntimeException(e.getMessage());
			}
		}
		// 否则直接包装成ResultVo返回
		return new ChokDto<Object>(data);
	}
}
