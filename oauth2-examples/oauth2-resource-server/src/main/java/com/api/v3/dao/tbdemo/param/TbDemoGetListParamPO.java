package com.api.v3.dao.tbdemo.param;

import com.api.v3.entity.TbDemo;

public class TbDemoGetListParamPO extends TbDemo
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] tcRowidArray;

	public String[] getTcRowidArray()
	{
		return tcRowidArray;
	}

	public void setTcRowidArray(String[] tcRowidArray)
	{
		this.tcRowidArray = tcRowidArray;
	}
	
}
