package com.api.v2.tbdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v2.dao.TbDemoDao;
import com.api.v2.entity.TbDemo;

import chok.devwork.BaseDao;
import chok.devwork.BaseService;

@Service(value = "v2TbDemoService")
public class TbDemoService extends BaseService<TbDemo,Long>
{
	@Autowired
	private TbDemoDao dao;

	@Override
	public BaseDao<TbDemo,Long> getEntityDao() 
	{
		return dao;
	}
	
	@Override
	public int add(TbDemo demo) throws Exception
	{
		dao.add(demo);
		throw new Exception("Error !!!");
	}
}
