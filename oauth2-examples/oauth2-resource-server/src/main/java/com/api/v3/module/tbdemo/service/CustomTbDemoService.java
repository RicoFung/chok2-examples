package com.api.v3.module.tbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v3.module.tbdemo.controller.dto.CustomDto;
import com.api.v3.module.tbdemo.controller.ro.RoMapper;
import com.api.v3.module.tbdemo.controller.ro.TbDemoGetListRo;
import com.api.v3.repository.tbdemo.dao.TbDemoDao;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetListParam;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetListPo;

@Service
public class CustomTbDemoService
{
	@Autowired
	TbDemoDao dao;
	
	public CustomDto<List<TbDemoGetListPo>> getList(TbDemoGetListRo ro) 
	{
		TbDemoGetListParam param = RoMapper.INSTANCE.roToParam(ro);
		List<TbDemoGetListPo> result = dao.getList(param);
		CustomDto<List<TbDemoGetListPo>> resultDTO = new CustomDto<List<TbDemoGetListPo>>();
		resultDTO.setData(result);
		return resultDTO;
	}
}
