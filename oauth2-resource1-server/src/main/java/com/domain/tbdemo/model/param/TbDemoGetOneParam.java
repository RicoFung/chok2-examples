package com.domain.tbdemo.model.param;

import java.io.Serializable;
import java.util.Arrays;
import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "TbDemoGetOneParam 详情入参")
public class TbDemoGetOneParam implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Schema(title = "动态列", example = "[\"tcRowid\",\"tcCode\",\"tcName\"]")
	private java.lang.String[] dynamicColumns;

	@Schema(title = "主键", example = "", required = true)
	@NotNull(message = "tcRowid不能为空！")
	private String tcRowid;

	public String[] getDynamicColumns()
	{
		return dynamicColumns;
	}
	public void setDynamicColumns(String[] dynamicColumns)
	{
		this.dynamicColumns = dynamicColumns;
	}

	public String getTcRowid() 
	{
		return this.tcRowid;
	}
	public void setTcRowid(String value) 
	{
		this.tcRowid = value;
	}
	
	@Override
	public String toString()
	{
		return "TbDemoGetOneParam [dynamicColumns=" + Arrays.toString(dynamicColumns) + ", tcRowid=" + tcRowid + "]";
	}
}
