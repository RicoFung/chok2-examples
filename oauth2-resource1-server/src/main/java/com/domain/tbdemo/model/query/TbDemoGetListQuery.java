package com.domain.tbdemo.model.query;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TbDemoGetListQuery implements Serializable
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
	
	public static class DynamicWhere implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
	    // tcCode       db_column: TC_CODE 
		private java.lang.String tcCode;
	    // tcName       db_column: TC_NAME 
		private java.lang.String tcName;
		
		// tcRowid       db_column: TC_ROWID 
		private String[] tcRowidArray;
		
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
	
		public String[] getTcRowidArray() 
		{
			return this.tcRowidArray;
		}
		public void setTcRowidArray(String[] value) 
		{
			this.tcRowidArray = value;
		}

		@Override
		public String toString()
		{
			return "DynamicWhere [" + 
					"tcCode=" + tcCode
					+ ", tcName=" + tcName
					+ ", tcRowidArray=" + Arrays.toString(tcRowidArray)
					+ "]";
		}
	}
	
	public String[] getDynamicColumns()
	{
		return dynamicColumns;
	}
	
	public void setDynamicColumns(String[] dynamicColumns)
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

	@Override
	public String toString()
	{
		return "TbDemoGetListQuery [dynamicColumns=" + Arrays.toString(dynamicColumns) + ", dynamicOrder="
				+ dynamicOrder + ", dynamicWhere=" + dynamicWhere.toString() + ", page=" + page + ", pagesize=" + pagesize + "]";
	}
}
