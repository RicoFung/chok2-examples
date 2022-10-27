package com.api.v1.tbdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.v1.tbdemo.dao.TbDemoDao;
import com.api.v1.tbdemo.entity.TbDemo;

import chok.devwork.BaseDao;
import chok.devwork.BaseService;

@Service(value = "v1TbDemoService")
public class TbDemoService extends BaseService<TbDemo, Long>
{
	@Autowired
	private TbDemoDao dao;

	@Override
	public BaseDao<TbDemo, Long> getEntityDao() 
	{
		return dao;
	}
}
