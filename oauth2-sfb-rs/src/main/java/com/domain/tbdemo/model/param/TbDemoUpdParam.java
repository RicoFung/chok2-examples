package com.domain.tbdemo.model.param;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "TbDemoUpdParam 修改入参")
public class TbDemoUpdParam implements Serializable
{
	private static final long serialVersionUID = 1L;

	// tcRowid       db_column: TC_ROWID 
	@Schema(title = "tcRowid", example = "")
	@NotNull(message = "tcRowid必传！")
	@NotBlank(message = "tcRowid不能为空！")
	private String tcRowid;
	
    // tcCode       db_column: TC_CODE 
    @Schema(title = "tcCode", example = "")
	//	@NotNull(message = "tcCode(tcCode)必传！")
	private java.lang.String tcCode;
    // tcName       db_column: TC_NAME 
    @Schema(title = "tcName", example = "")
	//	@NotNull(message = "tcName(tcName)必传！")
	private java.lang.String tcName;

	public String getTcRowid() 
	{
		return this.tcRowid;
	}
	public void setTcRowid(String value) 
	{
		this.tcRowid = value;
	}
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

