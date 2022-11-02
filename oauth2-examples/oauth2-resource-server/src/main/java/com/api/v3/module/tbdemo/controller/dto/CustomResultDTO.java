package com.api.v3.module.tbdemo.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import chok.common.RestConstants;
import chok.devwork.pojo.ChokResultBase;

// 按具体业务隐藏字段
@JsonIgnoreProperties(value = {"success", "code", "msg", "path", "timestamp", "data"})
public class CustomResultDTO<T> extends ChokResultBase<T>
{
	private static final long serialVersionUID = 1L;

	private CustomResultDTOEsbInfo	esbInfo	= new CustomResultDTOEsbInfo();
	private T					resultInfo;

	public CustomResultDTOEsbInfo getEsbInfo()
	{
		return esbInfo;
	}

	public void setEsbInfo(CustomResultDTOEsbInfo esbInfo)
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
			this.esbInfo.setReturnStatus(CustomResultConstants.SUCCESS_STATUS);
		}
		else
		{
			this.esbInfo.setReturnStatus(CustomResultConstants.ERROR_STATUS);
		}
	}

	@Override
	public void setCode(String code)
	{
		super.setCode(code);
		if (code.equals(RestConstants.SUCCESS_CODE))
		{
			this.esbInfo.setReturnCode(CustomResultConstants.SUCCESS_CODE);
		}
		else
		{
			this.esbInfo.setReturnCode(CustomResultConstants.ERROR_CODE);
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
