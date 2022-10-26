package com.api.v3.tbdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import chok.common.RestConstants;
import chok.devwork.springboot.pojo.ChokResultDTO;

public class ControllerHandler<T>
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	protected ObjectMapper objMapper = new ObjectMapper();
	
	public ChokResultDTO<T> execute(Object dto, BindingResult validResult, Callback<T> callback)
	{
		ChokResultDTO<T> resultDTO = new ChokResultDTO<T>();
		try
		{
			// 获取操作用户信息
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			// 创建时间
			Long tcTime = System.currentTimeMillis();
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", objMapper.writeValueAsString(dto));
			}
			if (validResult != null && validResult.hasErrors())
			{
				resultDTO.setSuccess(false);
				resultDTO.setCode(RestConstants.ERROR_CODE1);
				resultDTO.setMsg(getValidMsgs(validResult));
				return resultDTO;
			}
			// executeProcess
			resultDTO = callback.process(resultDTO, authentication, tcTime);
			if (resultDTO.isSuccess())
			{
				// executeSuccess
				callback.success(resultDTO);
			}
			else
			{
				// executeError
				callback.error(resultDTO);
				log.error(resultDTO.getMsg());
			}
		}
		catch (Exception e)
		{
			log.error("<== Exception：{}", e);
			resultDTO.setSuccess(false);
			resultDTO.setCode(RestConstants.ERROR_CODE1);
			resultDTO.setMsg(e.getMessage());
			// executeError
			callback.error(resultDTO);
		}
		finally
		{
			if (log.isDebugEnabled())
			{
				try
				{
					log.debug("<== get-responseDto：{}", objMapper.writeValueAsString(resultDTO));
				}
				catch (JsonProcessingException e)
				{
					log.error("<== JsonProcessingException: {}", e.getMessage());
				}
			}
		}
		return resultDTO;
	}
	
	protected String getValidMsgs(BindingResult validResult)
	{
		StringBuilder validMsgsBuilder = new StringBuilder();
		List<String> validMsgList = getValidMsgList(validResult);
		for (String validMsg : validMsgList)
		{
			validMsgsBuilder.append(validMsg + ";");
		}
		return StringUtils.removeEnd(validMsgsBuilder.toString(), ";");
	}
	
	protected List<String> getValidMsgList(BindingResult validResult)
	{
		List<String> validMsgList = new ArrayList<String>();
		for (ObjectError oError : validResult.getAllErrors())
		{
			validMsgList.add(oError.getDefaultMessage());
		}
		return validMsgList;
	}
	
	public abstract static class Callback<T>
	{
		/**
		 * 执行进行
		 * 
		 * @param resultDTO
		 * @param authentication
		 * @param tcTime
		 * @return
		 * @throws Exception
		 */
		protected abstract ChokResultDTO<T> process(ChokResultDTO<T> resultDTO,
				Authentication authentication, Long tcTime) throws Exception;

		/**
		 * 执行成功
		 * 
		 * @param resultDTO
		 * @return
		 */
		protected ChokResultDTO<T> success(ChokResultDTO<T> resultDTO)
		{
			return resultDTO;
		}

		/**
		 * 执行失败
		 * 
		 * @param resultDTO
		 * @return
		 */
		protected ChokResultDTO<T> error(ChokResultDTO<T> resultDTO)
		{
			return resultDTO;
		}

	}
}
