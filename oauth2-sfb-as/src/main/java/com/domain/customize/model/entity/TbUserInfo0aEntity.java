package com.domain.customize.model.entity;

import java.io.Serializable;
/**
 *
 * @author rico
 * @version 1.0
 * @since 1.0
 * */
public class TbUserInfo0aEntity implements Serializable
{
	private static final long serialVersionUID = -2345191839626132398L;
	
	// tcRowid       db_column: TC_ROWID 
	private java.lang.Long tcRowid;
    // 代号       db_column: TC_CODE 
	private java.lang.String tcCode;
    // 名称       db_column: TC_NAME 
	private java.lang.String tcName;
    // 密码       db_column: TC_PASSWORD 
	private java.lang.String tcPassword;
	
	public java.lang.Long getTcRowid()
	{
		return tcRowid;
	}
	public void setTcRowid(java.lang.Long tcRowid)
	{
		this.tcRowid = tcRowid;
	}
	public java.lang.String getTcCode()
	{
		return tcCode;
	}
	public void setTcCode(java.lang.String tcCode)
	{
		this.tcCode = tcCode;
	}
	public java.lang.String getTcName()
	{
		return tcName;
	}
	public void setTcName(java.lang.String tcName)
	{
		this.tcName = tcName;
	}
	public java.lang.String getTcPassword()
	{
		return tcPassword;
	}
	public void setTcPassword(java.lang.String tcPassword)
	{
		this.tcPassword = tcPassword;
	}
}

