package com.api.v3.tbdemo.pojo.param;

import java.io.Serializable;
import java.util.Arrays;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "TbDemoDelParamDTO 删除入参")
public class TbDemoDelParam implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "tcRowidArray不能为空！")
    @Size(min = 1, message = "最少选一条记录！")
	private String[] tcRowidArray;
	
	public String[] getTcRowidArray()
	{
		return tcRowidArray;
	}

	public void setTcRowidArray(String[] tcRowidArray)
	{
		this.tcRowidArray = tcRowidArray;
	}

	@Override
	public String toString()
	{
		return "TbDemoDelDTO [tcRowids=" + Arrays.toString(tcRowidArray) + "]";
	}
}
