package com.domain.tbdemo.model.param;

import java.util.Arrays;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "TbDemoExportParam 导出入参")
public class TbDemoExportParam extends TbDemoGetListParam
{
	private static final long serialVersionUID = 1L;

	// ********************************************************************************************
	// 0.文件参数
	// ********************************************************************************************
	@Schema(title = "文件名", example = "rpt")
	private java.lang.String rptName;
	
	@Schema(title = "文件格式：pdf/xlsx/html", example = "pdf")
	@NotNull(message = "rptFormat必传！")
	@NotBlank(message = "rptFormat不能为空！")
	private String rptFormat;

	public java.lang.String getRptName()
	{
		return rptName;
	}

	public void setRptName(java.lang.String rptName)
	{
		this.rptName = rptName;
	}

	public String getRptFormat()
	{
		return rptFormat;
	}

	public void setRptFormat(String rptFormat)
	{
		this.rptFormat = rptFormat;
	}

	@Override
	public String toString()
	{
		return "TbDemoExportParam [dynamicColumns=" + Arrays.toString(dynamicColumns) + ", dynamicOrder="
				+ dynamicOrder + ", dynamicWhere=" + dynamicWhere.toString() + ", page=" + page + ", pagesize=" + pagesize + "]";
	}
	
}
