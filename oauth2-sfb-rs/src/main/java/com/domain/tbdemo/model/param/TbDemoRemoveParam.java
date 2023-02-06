package com.domain.tbdemo.model.param;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "TbDemoRemoveParam 删除入参")
public class TbDemoRemoveParam implements Serializable
{
	private static final long serialVersionUID = 1L;

	@NotNull(message = "tcRowidArray必传！")
    @Size(min = 1, message = "最少选一条记录！")
	private String[] tcRowidArray;

	public String[] getTcRowidArray() 
	{
		return this.tcRowidArray;
	}
	public void setTcRowidArray(String[] value) 
	{
		this.tcRowidArray = value;
	}
}

