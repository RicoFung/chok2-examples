package com.domain.customize.model.query;

import java.io.Serializable;
import java.util.Arrays;

public class TbUserInfo0aGetOneByUsernameQuery implements Serializable
{
	private static final long serialVersionUID = 6980178311882981565L;

	private java.lang.String[] dynamicColumns;

	private java.lang.String username;

	public java.lang.String[] getDynamicColumns()
	{
		return dynamicColumns;
	}

	public void setDynamicColumns(java.lang.String[] dynamicColumns)
	{
		this.dynamicColumns = dynamicColumns;
	}

	public java.lang.String getUsername()
	{
		return username;
	}

	public void setUsername(java.lang.String username)
	{
		this.username = username;
	}

	@Override
	public String toString()
	{
		return "TbUserInfo0aGetOneByUsernameQuery [dynamicColumns=" + Arrays.toString(dynamicColumns) + ", username="
				+ username + "]";
	}
}
