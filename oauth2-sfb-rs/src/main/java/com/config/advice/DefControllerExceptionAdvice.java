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

import chok2.devwork.pojo.ChokDto;
import chok2.devwork.pojo.ChokDtoConstants;

/*******************************************
 * 
 * 只能拦截到Controller层的异常，且Controller自行try catch的话会无效
 * @author rico.fung
 *
 */
@RestControllerAdvice(basePackages = {"com.domain.tbdemo.controller"})
public class DefControllerExceptionAdvice
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ChokDto<Object> defaultErrorHandler(HttpServletRequest req, Exception e)
	{
		ChokDto<Object> dto = new ChokDto<Object>();
		dto.setSuccess(false);
		dto.setCode(ChokDtoConstants.ERROR_CODE1);
		dto.setMsg(e.toString());
		log.error("{}", e);
		return dto;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ChokDto<Object> validationExceptions(MethodArgumentNotValidException e)
	{
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) ->
		{
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		ChokDto<Object> dto = new ChokDto<Object>();
		dto.setSuccess(false);
		dto.setCode(ChokDtoConstants.ERROR_CODE1);
		dto.setMsg(errors.values().toString());
		log.error("{}", errors.values().toString());
		return dto;
	}
}
