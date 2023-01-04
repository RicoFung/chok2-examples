package com.api.v3.customtbdemo.domain.result;

import com.api.v3.customtbdemo.domain.entity.CustomTbDemoEntity;

public class CustomTbDemoGetListResult extends CustomTbDemoEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString()
	{
		return "TbDemoGetListResultPO [getTcRowid()=" + getTcRowid() + ", getTcCode()=" + getTcCode() + ", getTcName()="
				+ getTcName() + "]";
	}

}
