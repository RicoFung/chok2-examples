package com.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*******************************************
 * 
 * 只能拦截到Controller层的异常，且Controller自行try catch的话会无效
 * @author rico.fung
 *
 */
@ControllerAdvice(basePackages = {"com"})
public class ControllerExceptionAdvice
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(value = Exception.class)
	public String errorHandler(Model model, HttpServletRequest request, Exception e)
	{
		logger.error("<== Exception: {}", e.getMessage());
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
	}
}
