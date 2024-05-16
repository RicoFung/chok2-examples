package com.domain.error.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalErrorController implements ErrorController
{
	@RequestMapping("/error")
	public String error(Model model, HttpServletRequest request)
	{
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = "";
        if (!ObjectUtils.isEmpty(request.getAttribute(RequestDispatcher.ERROR_MESSAGE)))
        {
            message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        }
        else
        {
        	message = request.getAttribute("errorMessage");
        }
        model.addAttribute("errorMessage", "Error " + status + ": " + message);
        return "error";
	}
}
