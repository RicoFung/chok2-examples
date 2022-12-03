package com.api.v3.customtbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v3.customtbdemo.dao.CustomTbDemoReadDao;
import com.api.v3.customtbdemo.dto.CustomDto;
import com.api.v3.customtbdemo.pojo.param.CustomTbDemoGetListParam;
import com.api.v3.customtbdemo.pojo.result.CustomTbDemoGetListResult;

@Service
public class CustomTbDemoService
{
	@Autowired
	CustomTbDemoReadDao dao;
	
	public CustomDto<List<CustomTbDemoGetListResult>> getList(CustomTbDemoGetListParam param) 
	{
		List<CustomTbDemoGetListResult> result = dao.getList(param);
		CustomDto<List<CustomTbDemoGetListResult>> resultDTO = new CustomDto<List<CustomTbDemoGetListResult>>();
		resultDTO.setData(result);
		return resultDTO;
	}
}