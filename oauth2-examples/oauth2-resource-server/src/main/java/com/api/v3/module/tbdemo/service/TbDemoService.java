package com.api.v3.module.tbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v3.module.tbdemo.controller.dto.CustomResultDTO;
import com.api.v3.module.tbdemo.controller.ro.RoMapper;
import com.api.v3.module.tbdemo.controller.ro.TbDemoAddRO;
import com.api.v3.module.tbdemo.controller.ro.TbDemoDelRO;
import com.api.v3.module.tbdemo.controller.ro.TbDemoGetListRO;
import com.api.v3.module.tbdemo.controller.ro.TbDemoGetOneRO;
import com.api.v3.module.tbdemo.controller.ro.TbDemoUpdRO;
import com.api.v3.repository.tbdemo.dao.TbDemoDao;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetListParam;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetOneParam;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetListPO;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetOnePO;
import com.api.v3.repository.tbdemo.entity.TbDemo;

import chok.devwork.pojo.ChokResultDTO;

@Service
public class TbDemoService
{
	@Autowired
	TbDemoDao dao;
	
	public ChokResultDTO<Object> add(TbDemoAddRO ro)
	{
		TbDemo po = RoMapper.INSTANCE.roToEntity(ro);
		dao.add(po);
		ChokResultDTO<Object> resultDTO = new ChokResultDTO<Object>();
		return resultDTO;
	}
	
	public ChokResultDTO<Object> del(TbDemoDelRO ro)
	{
		dao.del(ro.getTcRowidArray());
		ChokResultDTO<Object> resultDTO = new ChokResultDTO<Object>();
		return resultDTO;
	}
	
	public ChokResultDTO<Object> upd(TbDemoUpdRO ro)
	{
		TbDemo po = RoMapper.INSTANCE.roToEntity(ro);
		dao.upd(po);
		ChokResultDTO<Object> resultDTO = new ChokResultDTO<Object>();
		return resultDTO;
	}	
	
	public ChokResultDTO<TbDemoGetOnePO> getOne(TbDemoGetOneRO ro) 
	{
		TbDemoGetOneParam param = RoMapper.INSTANCE.roToParam(ro);
		TbDemoGetOnePO result = dao.getOne(param);
		ChokResultDTO<TbDemoGetOnePO> resultDTO = new ChokResultDTO<TbDemoGetOnePO>();
		resultDTO.setData(result);
		return resultDTO;
	}
	
	public ChokResultDTO<List<TbDemoGetListPO>> getList(TbDemoGetListRO ro) 
	{
		TbDemoGetListParam param = RoMapper.INSTANCE.roToParam(ro);
		List<TbDemoGetListPO> result = dao.getList(param);
		ChokResultDTO<List<TbDemoGetListPO>> resultDTO = new ChokResultDTO<List<TbDemoGetListPO>>();
		resultDTO.setData(result);
		return resultDTO;
	}
	
	public CustomResultDTO<List<TbDemoGetListPO>> getEpoList(TbDemoGetListRO ro) 
	{
		TbDemoGetListParam param = RoMapper.INSTANCE.roToParam(ro);
		List<TbDemoGetListPO> result = dao.getList(param);
		CustomResultDTO<List<TbDemoGetListPO>> resultDTO = new CustomResultDTO<List<TbDemoGetListPO>>();
		resultDTO.setData(result);
		return resultDTO;
	}
}
