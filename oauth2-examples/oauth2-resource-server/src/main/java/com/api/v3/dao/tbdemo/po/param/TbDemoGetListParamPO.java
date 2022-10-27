package com.api.v3.dao.tbdemo.po.param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.api.v3.entity.TbDemo;

public class TbDemoGetListParamPO implements Serializable
{
	private static final long serialVersionUID = 1L;

	// ********************************************************************************************
	// 1.默认参数
	// ********************************************************************************************
	private java.lang.String[] dynamicColumns;
	private List<Map<String, Object>> dynamicOrder;
	private DynamicWhere dynamicWhere;
	private int page;
	private int pagesize;
	
	// ********************************************************************************************
	// 2.表单参数
	// ********************************************************************************************
	public static class DynamicWhere extends TbDemo
	{
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

	public java.lang.String[] getDynamicColumns()
	{
		return dynamicColumns;
	}

	public void setDynamicColumns(java.lang.String[] dynamicColumns)
	{
		this.dynamicColumns = dynamicColumns;
	}

	public List<Map<String, Object>> getDynamicOrder()
	{
		return dynamicOrder;
	}

	public void setDynamicOrder(List<Map<String, Object>> dynamicOrder)
	{
		this.dynamicOrder = dynamicOrder;
	}

	public DynamicWhere getDynamicWhere()
	{
		return dynamicWhere;
	}

	public void setDynamicWhere(DynamicWhere dynamicWhere)
	{
		this.dynamicWhere = dynamicWhere;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(int pagesize)
	{
		this.pagesize = pagesize;
	}
	
}
