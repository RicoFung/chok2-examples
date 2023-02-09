package com.config.advice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.domain.customtbdemo.dto.CustomDto;

import chok.devwork.pojo.ChokDtoConstants;

/*******************************************
 * 
 * 只能拦截到Controller层的异常，且Controller自行try catch的话会无效
 * @author rico.fung
 *
 */
@RestControllerAdvice(basePackages = {"com.api.v3.customtbdemo.controller"})
public class CusControllerExceptionAdvice
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public CustomDto<Object> defaultErrorHandler(HttpServletRequest req, Exception e)
	{
		CustomDto<Object> dto = new CustomDto<Object>();
		dto.setSuccess(false);
		dto.setCode(ChokDtoConstants.ERROR_CODE1);
		dto.setMsg(e.toString());
		log.error("{}", e);
		return dto;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public CustomDto<Object> validationExceptions(MethodArgumentNotValidException e)
	{
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) ->
		{
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		CustomDto<Object> dto = new CustomDto<Object>();
		dto.setSuccess(false);
		dto.setCode(ChokDtoConstants.ERROR_CODE1);
		dto.setMsg(errors.values().toString());
		log.error("{}", errors.values().toString());
		return dto;
	}
}
