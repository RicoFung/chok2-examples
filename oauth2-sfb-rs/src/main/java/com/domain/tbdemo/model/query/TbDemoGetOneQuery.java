package com.domain.tbdemo.model.query;

import java.io.Serializable;
import java.util.Arrays;

public class TbDemoGetOneQuery implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private java.lang.String[] dynamicColumns;

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
		return "TbDemoGetOneQuery [dynamicColumns=" + Arrays.toString(dynamicColumns) + ", tcRowid=" + tcRowid + "]";
	}
}
