package com.api.v3.module.tbdemo.controller.ro;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "TbDemoGetListParamDTO 列表入参")
public class TbDemoGetListRO implements Serializable
{
	private static final long serialVersionUID = 1L;

	// ********************************************************************************************
	// 1.默认参数
	// ********************************************************************************************

	@Schema(title = "动态列", example = "[\"tcRowid\",\"tcCode\",\"tcName\"]")//, position = 0)
	private java.lang.String[] dynamicColumns;
	
	@Schema(title = "动态排序", example = "[{\"sortName\":\"tcRowid\",\"sortOrder\":\"DESC\"}]")//, position = 1)
	private List<Map<String, Object>> dynamicOrder;
	
	@Schema(title = "动态过滤")//, position = 2)
	private DynamicWhere dynamicWhere;
	
	@Schema(title = "页码", example = "1")//, position = 2)
	private int page;

	@Schema(title = "页大小", example = "10")//, position = 3)
	private int pagesize;

	// ********************************************************************************************
	// 2.表单参数
	// ********************************************************************************************
	
	public static class DynamicWhere implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
		// tcCode       db_column: TC_CODE 
		@Schema(title = "tcCode", example = "")//, position = 5)
		private java.lang.String tcCode;
		// tcName       db_column: TC_NAME 
		@Schema(title = "tcName", example = "")//, position = 6)
		private java.lang.String tcName;
		
		private String[] tcRowidArray;
		
		public void setTcCode(java.lang.String value) 
		{
			this.tcCode = value;
		}
		
		public java.lang.String getTcCode() 
		{
			return this.tcCode;
		}
		public void setTcName(java.lang.String value) 
		{
			this.tcName = value;
		}
		
		public java.lang.String getTcName() 
		{
			return this.tcName;
		}
		
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

