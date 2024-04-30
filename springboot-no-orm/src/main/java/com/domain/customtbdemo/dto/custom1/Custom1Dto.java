package com.domain.customtbdemo.dto.custom1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import chok2.devwork.pojo.ChokResponseBase;
import chok2.devwork.pojo.ChokResponseConstants;

// 按具体业务隐藏字段
@JsonIgnoreProperties(value = { "success", "msg", "path", "timestamp" })
public class Custom1Dto<T> extends ChokResponseBase<T>
{
	private static final long serialVersionUID = 1L;

	@Override
	public String getCode()
	{
		switch (super.getCode()) 
		{
			case ChokResponseConstants.SUCCESS_CODE: 
				return "0"; 
			default: 
				return "1";
		}
	}

	@Override
	public T getData()
	{
		return super.getData();
	}

}
