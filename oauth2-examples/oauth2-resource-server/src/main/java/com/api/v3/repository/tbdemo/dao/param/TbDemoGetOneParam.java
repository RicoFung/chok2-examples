package com.api.v3.repository.tbdemo.dao.param;

import java.io.Serializable;

public class TbDemoGetOneParam implements Serializable
{
	private static final long serialVersionUID = 1L;

	// ********************************************************************************************
	// 1.默认参数
	// ********************************************************************************************
	private java.lang.String[] dynamicColumns;
	
	private java.lang.Long tcRowid;

	public String[] getDynamicColumns()
	{
		return dynamicColumns;
	}
	
	public void setDynamicColumns(String[] dynamicColumns)
	{
		this.dynamicColumns = dynamicColumns;
	}
	
	public void setTcRowid(java.lang.Long value)
	{
		this.tcRowid = value;
	}

	public java.lang.Long getTcRowid()
	{
		return this.tcRowid;
	}

}
