package com.api.v3.tbdemo.dto;

import com.api.v3.entity.TbDemo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "TbDemoGetOneParamDTO 详情入参")
public class TbDemoGetOneParamDTO extends TbDemo
{
	private static final long serialVersionUID = 1L;
	
//	@Schema(title = "动态列", example = "[\"tcRowid\",\"tcCode\",\"tcName\"]")//, position = 0)
//	private java.lang.String[] dynamicColumns;
//
//	@Schema(title = "主键", example = "0")//, position = 1, required = true)
//	@NotNull(message = "tcRowid不能为空！")
//	private java.lang.Long tcRowid;
//
//	public String[] getDynamicColumns()
//	{
//		return dynamicColumns;
//	}
//	
//	public void setDynamicColumns(String[] dynamicColumns)
//	{
//		this.dynamicColumns = dynamicColumns;
//	}
//	
//	public void setTcRowid(java.lang.Long value)
//	{
//		this.tcRowid = value;
//	}
//
//	public java.lang.Long getTcRowid()
//	{
//		return this.tcRowid;
//	}
}
