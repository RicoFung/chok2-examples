package com.api.v3.tbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v3.dao.tbdemo.TbDemoDao;
import com.api.v3.dao.tbdemo.po.TbDemoGetListParamPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetListResultPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetOneParamPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetOneResultPO;
import com.api.v3.tbdemo.dto.ParamMapper;
import com.api.v3.tbdemo.dto.TbDemoGetListParamDTO;
import com.api.v3.tbdemo.dto.TbDemoGetListResultDTO;
import com.api.v3.tbdemo.dto.TbDemoGetOneParamDTO;
import com.api.v3.tbdemo.dto.TbDemoGetOneResultDTO;

@Service
public class TbDemoService
{
	@Autowired
	TbDemoDao dao;
	
	public TbDemoGetOneResultDTO getOne(TbDemoGetOneParamDTO paramDTO) 
	{
		TbDemoGetOneParamPO paramPO = ParamMapper.INSTANCE.dtoToPo(paramDTO);
		TbDemoGetOneResultPO result = dao.getOne(paramPO);
		TbDemoGetOneResultDTO resultDTO = new TbDemoGetOneResultDTO();
		resultDTO.setData(result);
		return resultDTO;
	}
	
	public TbDemoGetListResultDTO getList(TbDemoGetListParamDTO paramDTO) 
	{
		TbDemoGetListParamPO paramPO = ParamMapper.INSTANCE.dtoToPo(paramDTO);
		List<TbDemoGetListResultPO> result = dao.getList(paramPO);
		TbDemoGetListResultDTO resultDTO = new TbDemoGetListResultDTO();
		resultDTO.setData(result);
		return resultDTO;
	}
	
}
