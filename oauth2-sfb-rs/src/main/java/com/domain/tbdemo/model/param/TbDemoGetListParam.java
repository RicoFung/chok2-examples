package com.domain.tbdemo.model.param;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "TbDemoGetListParam 列表入参")
public class TbDemoGetListParam implements Serializable
{
	private static final long serialVersionUID = 1L;

	// ********************************************************************************************
	// 1.默认参数
	// ********************************************************************************************

	@Schema(title = "动态列", example = "[\"tcRowid\",\"tcCode\",\"tcName\"]")
	protected java.lang.String[] dynamicColumns;
	
	@Schema(title = "动态排序", example = "[{\"sortName\":\"tcRowid\",\"sortOrder\":\"DESC\"}]")
	protected List<Map<String, Object>> dynamicOrder;
	
	@Schema(title = "动态过滤")
	protected DynamicWhere dynamicWhere;

	@Schema(title = "页码", example = "1")
	protected int page;

	@Schema(title = "页大小", example = "10")
	protected int pagesize;

	// ********************************************************************************************
	// 2.表单参数
	// ********************************************************************************************
	
	public static class DynamicWhere implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
	    // tcCode       db_column: TC_CODE 
		@Schema(title = "tcCode", example = "")
		private java.lang.String tcCode;
	    // tcName       db_column: TC_NAME 
		@Schema(title = "tcName", example = "")
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
		return "TbDemoGetListParam [dynamicColumns=" + Arrays.toString(dynamicColumns) + ", dynamicOrder="
				+ dynamicOrder + ", dynamicWhere=" + dynamicWhere.toString() + ", page=" + page + ", pagesize=" + pagesize + "]";
	}
}
