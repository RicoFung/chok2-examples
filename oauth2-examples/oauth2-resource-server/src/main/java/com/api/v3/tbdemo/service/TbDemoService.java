package com.api.v3.tbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v3.dao.tbdemo.TbDemoDao;
import com.api.v3.dao.tbdemo.po.TbDemoGetListParamPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetListResultPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetOneParamPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetOneResultPO;
import com.api.v3.entity.TbDemo;
import com.api.v3.tbdemo.dto.ParamMapper;
import com.api.v3.tbdemo.dto.TbDemoAddParamDTO;
import com.api.v3.tbdemo.dto.TbDemoDelParamDTO;
import com.api.v3.tbdemo.dto.TbDemoGetListParamDTO;
import com.api.v3.tbdemo.dto.TbDemoGetOneParamDTO;
import com.api.v3.tbdemo.dto.TbDemoUpdParamDTO;

import chok.devwork.springboot.pojo.ChokResultDTO;

@Service
public class TbDemoService
{
	@Autowired
	TbDemoDao dao;
	
	public ChokResultDTO<Object> add(TbDemoAddParamDTO paramDTO)
	{
		TbDemo po = ParamMapper.INSTANCE.dtoToPo(paramDTO);
		dao.add(po);
		ChokResultDTO<Object> resultDTO = new ChokResultDTO<Object>();
		return resultDTO;
	}
	
	public ChokResultDTO<Object> del(TbDemoDelParamDTO paramDTO)
	{
		dao.del(paramDTO.getTcRowidArray());
		ChokResultDTO<Object> resultDTO = new ChokResultDTO<Object>();
		return resultDTO;
	}
	
	public ChokResultDTO<Object> upd(TbDemoUpdParamDTO paramDTO)
	{
		TbDemo po = ParamMapper.INSTANCE.dtoToPo(paramDTO);
		dao.upd(po);
		ChokResultDTO<Object> resultDTO = new ChokResultDTO<Object>();
		return resultDTO;
	}	
	
	public ChokResultDTO<TbDemoGetOneResultPO> getOne(TbDemoGetOneParamDTO paramDTO) 
	{
		TbDemoGetOneParamPO paramPO = ParamMapper.INSTANCE.dtoToPo(paramDTO);
		TbDemoGetOneResultPO result = dao.getOne(paramPO);
		ChokResultDTO<TbDemoGetOneResultPO> resultDTO = new ChokResultDTO<TbDemoGetOneResultPO>();
		resultDTO.setData(result);
		return resultDTO;
	}
	
	public ChokResultDTO<List<TbDemoGetListResultPO>> getList(TbDemoGetListParamDTO paramDTO) 
	{
		TbDemoGetListParamPO paramPO = ParamMapper.INSTANCE.dtoToPo(paramDTO);
		List<TbDemoGetListResultPO> result = dao.getList(paramPO);
		ChokResultDTO<List<TbDemoGetListResultPO>> resultDTO = new ChokResultDTO<List<TbDemoGetListResultPO>>();
		resultDTO.setData(result);
		return resultDTO;
	}
}
