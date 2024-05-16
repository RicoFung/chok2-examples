package com.domain.tbdemo.model.entity;

import java.io.Serializable;
/**
 *
 * @author rico
 * @version 1.0
 * @since 1.0
 * */
public class TbDemoEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	// tcRowid       db_column: TC_ROWID 
	private String tcRowid;

    // tcCode       db_column: TC_CODE 
	private java.lang.String tcCode;
    // tcName       db_column: TC_NAME 
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

