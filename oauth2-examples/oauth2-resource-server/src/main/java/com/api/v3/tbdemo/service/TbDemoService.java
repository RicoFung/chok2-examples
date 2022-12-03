package com.api.v3.tbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v3.tbdemo.dao.TbDemoReadDao;
import com.api.v3.tbdemo.dao.TbDemoWriteDao;
import com.api.v3.tbdemo.pojo.TbDemo;
import com.api.v3.tbdemo.pojo.param.ParamMapper;
import com.api.v3.tbdemo.pojo.param.TbDemoAddParam;
import com.api.v3.tbdemo.pojo.param.TbDemoDelParam;
import com.api.v3.tbdemo.pojo.param.TbDemoGetListParam;
import com.api.v3.tbdemo.pojo.param.TbDemoGetOneParam;
import com.api.v3.tbdemo.pojo.param.TbDemoUpdParam;
import com.api.v3.tbdemo.pojo.result.TbDemoGetListResult;
import com.api.v3.tbdemo.pojo.result.TbDemoGetOneResult;

import chok.devwork.pojo.ChokDto;

@Service
public class TbDemoService
{
	@Autowired
	TbDemoWriteDao wDao;
	@Autowired
	TbDemoReadDao rDao;
	
	public ChokDto<Object> add(TbDemoAddParam param)
	{
		TbDemo entity = ParamMapper.INSTANCE.paramToEntity(param);
		wDao.add(entity);
		ChokDto<Object> dto = new ChokDto<Object>();
		return dto;
	}
	
	public ChokDto<Object> del(TbDemoDelParam param)
	{
		wDao.del(param.getTcRowidArray());
		ChokDto<Object> dto = new ChokDto<Object>();
		return dto;
	}
	
	public ChokDto<Object> upd(TbDemoUpdParam param)
	{
		TbDemo entity = ParamMapper.INSTANCE.paramToEntity(param);
		wDao.upd(entity);
		ChokDto<Object> dto = new ChokDto<Object>();
		return dto;
	}	
	
	public ChokDto<TbDemoGetOneResult> getOne(TbDemoGetOneParam param) 
	{
		TbDemoGetOneResult result = rDao.getOne(param);
		ChokDto<TbDemoGetOneResult> dto = new ChokDto<TbDemoGetOneResult>();
		dto.setData(result);
		return dto;
	}
	
	public ChokDto<List<TbDemoGetListResult>> getList(TbDemoGetListParam param) 
	{
		List<TbDemoGetListResult> result = rDao.getList(param);
		ChokDto<List<TbDemoGetListResult>> dto = new ChokDto<List<TbDemoGetListResult>>();
		dto.setData(result);
		return dto;
	}
}
