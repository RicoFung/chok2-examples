package com.domain.customtbdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import chok.common.RestConstants;
import chok.devwork.pojo.ChokDtoBase;

// 按具体业务隐藏字段
@JsonIgnoreProperties(value = {"success", "code", "msg", "path", "timestamp", "data"})
public class CustomDto<T> extends ChokDtoBase<T>
{
	private static final long serialVersionUID = 1L;

	private CustomDtoEsbInfo	esbInfo	= new CustomDtoEsbInfo();
	private T					resultInfo;

	public CustomDtoEsbInfo getEsbInfo()
	{
		return esbInfo;
	}

	public void setEsbInfo(CustomDtoEsbInfo esbInfo)
	{
		this.esbInfo = esbInfo;
	}

	public T getResultInfo()
	{
		return resultInfo;
	}

	public void setResultInfo(T resultInfo)
	{
		this.resultInfo = resultInfo;
	}

	@Override
	public void setSuccess(boolean success)
	{
		super.setSuccess(success);
		if (success == true)
		{
			this.esbInfo.setReturnStatus(CustomDtoConstants.SUCCESS_STATUS);
		}
		else
		{
			this.esbInfo.setReturnStatus(CustomDtoConstants.ERROR_STATUS);
		}
	}

	@Override
	public void setCode(String code)
	{
		super.setCode(code);
		if (code.equals(RestConstants.SUCCESS_CODE))
		{
			this.esbInfo.setReturnCode(CustomDtoConstants.SUCCESS_CODE);
		}
		else
		{
			this.esbInfo.setReturnCode(CustomDtoConstants.ERROR_CODE);
		}
	}

	@Override
	public void setMsg(String msg)
	{
		super.setMsg(msg);
		this.esbInfo.setReturnMsg(msg);
	}

	@Override
	public void setData(T data)
	{
		super.setData(data);
		this.setResultInfo(data);
	}

}
