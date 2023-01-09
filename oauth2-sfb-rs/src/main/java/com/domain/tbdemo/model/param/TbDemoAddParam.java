package com.domain.tbdemo.model.param;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "TbDemoAddParam 新增入参")
public class TbDemoAddParam implements Serializable
{
	private static final long serialVersionUID = 1L;

    // tcCode       db_column: TC_CODE 
	@Schema(title = "tcCode", example = "")
	@NotNull(message = "tcCode必传！")
	@NotBlank(message = "tcCode不能为空！")
	private java.lang.String tcCode;
    // tcName       db_column: TC_NAME 
	@Schema(title = "tcName", example = "")
	@NotNull(message = "tcName必传！")
	@NotBlank(message = "tcName不能为空！")
	private java.lang.String tcName;

	public java.lang.String getTcCode() 
	{
		return this.tcCode;
	}
	public void setTcCode(java.lang.String value) 
	{
		this.tcCode = value;
	}
	public java.lang.String getTcName() 
	{
		return this.tcName;
	}
	public void setTcName(java.lang.String value) 
	{
		this.tcName = value;
	}
}

