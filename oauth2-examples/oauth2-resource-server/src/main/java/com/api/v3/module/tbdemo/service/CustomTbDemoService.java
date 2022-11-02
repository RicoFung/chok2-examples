package com.api.v3.module.tbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v3.module.tbdemo.controller.dto.CustomResultDTO;
import com.api.v3.module.tbdemo.controller.ro.RoMapper;
import com.api.v3.module.tbdemo.controller.ro.TbDemoGetListRO;
import com.api.v3.repository.tbdemo.dao.TbDemoDao;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetListParam;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetListPO;

@Service
public class CustomTbDemoService
{
	@Autowired
	TbDemoDao dao;
	
	public CustomResultDTO<List<TbDemoGetListPO>> getList(TbDemoGetListRO ro) 
	{
		TbDemoGetListParam param = RoMapper.INSTANCE.roToParam(ro);
		List<TbDemoGetListPO> result = dao.getList(param);
		CustomResultDTO<List<TbDemoGetListPO>> resultDTO = new CustomResultDTO<List<TbDemoGetListPO>>();
		resultDTO.setData(result);
		return resultDTO;
	}
}
