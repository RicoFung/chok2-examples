package com.api.v3.tbdemo.dto.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import chok.common.RestConstants;
import chok.devwork.pojo.ChokResultBase;

// 按具体业务隐藏字段
@JsonIgnoreProperties(value = {"success", "code", "msg", "path", "timestamp", "data"})
public class EpoResultDTO<T> extends ChokResultBase<T>
{
	private static final long serialVersionUID = 1L;

	private EpoResultDTOEsbInfo	esbInfo	= new EpoResultDTOEsbInfo();
	private T					resultInfo;

	public EpoResultDTOEsbInfo getEsbInfo()
	{
		return esbInfo;
	}

	public void setEsbInfo(EpoResultDTOEsbInfo esbInfo)
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
			this.esbInfo.setReturnStatus(EpoEsbInfoConstants.SUCCESS_STATUS);
		}
		else
		{
			this.esbInfo.setReturnStatus(EpoEsbInfoConstants.ERROR_STATUS);
		}
	}

	@Override
	public void setCode(String code)
	{
		super.setCode(code);
		if (code.equals(RestConstants.SUCCESS_CODE))
		{
			this.esbInfo.setReturnCode(EpoEsbInfoConstants.SUCCESS_CODE);
		}
		else
		{
			this.esbInfo.setReturnCode(EpoEsbInfoConstants.ERROR_CODE);
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
