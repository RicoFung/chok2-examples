package com.api.v3.tbdemo.domain.query;

import java.io.Serializable;
import java.util.Arrays;

public class TbDemoGetOneQuery implements Serializable
{
	private static final long serialVersionUID = 1L;
	
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

	@Override
	public String toString()
	{
		return "TbDemoGetOneQuery [dynamicColumns=" + Arrays.toString(dynamicColumns) + ", tcRowid=" + tcRowid + "]";
	}
	
}
