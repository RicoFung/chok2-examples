package com.api.v3.module.tbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v3.module.tbdemo.controller.dto.CustomDto;
import com.api.v3.module.tbdemo.controller.ro.RoMapper;
import com.api.v3.module.tbdemo.controller.ro.TbDemoAddRo;
import com.api.v3.module.tbdemo.controller.ro.TbDemoDelRo;
import com.api.v3.module.tbdemo.controller.ro.TbDemoGetListRo;
import com.api.v3.module.tbdemo.controller.ro.TbDemoGetOneRo;
import com.api.v3.module.tbdemo.controller.ro.TbDemoUpdRo;
import com.api.v3.repository.tbdemo.dao.TbDemoDao;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetListParam;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetOneParam;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetListPo;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetOnePo;
import com.api.v3.repository.tbdemo.entity.TbDemo;

import chok.devwork.pojo.ChokDto;

@Service
public class TbDemoService
{
	@Autowired
	TbDemoDao dao;
	
	public ChokDto<Object> add(TbDemoAddRo ro)
	{
		TbDemo entity = RoMapper.INSTANCE.roToEntity(ro);
		dao.add(entity);
		ChokDto<Object> dto = new ChokDto<Object>();
		return dto;
	}
	
	public ChokDto<Object> del(TbDemoDelRo ro)
	{
		dao.del(ro.getTcRowidArray());
		ChokDto<Object> dto = new ChokDto<Object>();
		return dto;
	}
	
	public ChokDto<Object> upd(TbDemoUpdRo ro)
	{
		TbDemo entity = RoMapper.INSTANCE.roToEntity(ro);
		dao.upd(entity);
		ChokDto<Object> dto = new ChokDto<Object>();
		return dto;
	}	
	
	public ChokDto<TbDemoGetOnePo> getOne(TbDemoGetOneRo ro) 
	{
		TbDemoGetOneParam param = RoMapper.INSTANCE.roToParam(ro);
		TbDemoGetOnePo po = dao.getOne(param);
		ChokDto<TbDemoGetOnePo> dto = new ChokDto<TbDemoGetOnePo>();
		dto.setData(po);
		return dto;
	}
	
	public ChokDto<List<TbDemoGetListPo>> getList(TbDemoGetListRo ro) 
	{
		TbDemoGetListParam param = RoMapper.INSTANCE.roToParam(ro);
		List<TbDemoGetListPo> po = dao.getList(param);
		ChokDto<List<TbDemoGetListPo>> dto = new ChokDto<List<TbDemoGetListPo>>();
		dto.setData(po);
		return dto;
	}
	
	public CustomDto<List<TbDemoGetListPo>> getEpoList(TbDemoGetListRo ro) 
	{
		TbDemoGetListParam param = RoMapper.INSTANCE.roToParam(ro);
		List<TbDemoGetListPo> po = dao.getList(param);
		CustomDto<List<TbDemoGetListPo>> dto = new CustomDto<List<TbDemoGetListPo>>();
		dto.setData(po);
		return dto;
	}
}
