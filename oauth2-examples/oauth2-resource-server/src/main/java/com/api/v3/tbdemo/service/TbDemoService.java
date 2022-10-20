package com.api.v3.tbdemo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v3.dao.tbdemo.TbDemoDao;
import com.api.v3.dao.tbdemo.param.TbDemoGetListParamPO;
import com.api.v3.dao.tbdemo.param.TbDemoGetOneParamPO;
import com.api.v3.entity.TbDemoGetListResult;
import com.api.v3.entity.TbDemoGetOneResult;
import com.api.v3.tbdemo.service.param.TbDemoGetListParamBO;
import com.api.v3.tbdemo.service.param.TbDemoGetOneParamBO;
import com.api.v3.tbdemo.service.result.TbDemoGetOneResultBO;

import chok.devwork.springboot.pojo.ChokResultBO;

@Service
public class TbDemoService
{
	@Autowired
	TbDemoDao dao;
	
	public TbDemoGetOneResultBO getOne(TbDemoGetOneParamBO paramBO) 
	{
		TbDemoGetOneParamPO paramPO = new TbDemoGetOneParamPO();
		BeanUtils.copyProperties(paramBO, paramPO);
		
		TbDemoGetOneResult result = dao.getOne(paramPO);
		
		TbDemoGetOneResultBO resultBO = new TbDemoGetOneResultBO();
		resultBO.setData(result);
		
		return resultBO;
	}
	
	public ChokResultBO<List<TbDemoGetListResult>> getList(TbDemoGetListParamBO paramBO) 
	{
		TbDemoGetListParamPO paramPO = new TbDemoGetListParamPO();
		BeanUtils.copyProperties(paramBO, paramPO);
		
		List<TbDemoGetListResult> result = dao.getList(paramPO);
		
		ChokResultBO<List<TbDemoGetListResult>> chokResultBO = new ChokResultBO<List<TbDemoGetListResult>>();
		chokResultBO.setData(result);
		
		return chokResultBO;
	}
	
}
