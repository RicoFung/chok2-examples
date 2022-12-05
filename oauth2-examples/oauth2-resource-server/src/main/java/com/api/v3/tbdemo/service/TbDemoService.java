package com.api.v3.tbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

@CacheConfig(cacheNames = {"Cache_TbDemo"})
@Service
public class TbDemoService
{
	@Autowired
	TbDemoWriteDao wDao;
	@Autowired
	TbDemoReadDao rDao;

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> add(TbDemoAddParam param)
	{
		TbDemo entity = ParamMapper.INSTANCE.paramToEntity(param);
		wDao.add(entity);
		ChokDto<Object> dto = new ChokDto<Object>();
		return dto;
	}

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> del(TbDemoDelParam param)
	{
		wDao.del(param.getTcRowidArray());
		ChokDto<Object> dto = new ChokDto<Object>();
		return dto;
	}

	@Caching(evict = { @CacheEvict(value = {"Cache_TbDemo", "Cache_CustomTbDemo"}, allEntries = true) })
	public ChokDto<Object> upd(TbDemoUpdParam param)
	{
		TbDemo entity = ParamMapper.INSTANCE.paramToEntity(param);
		wDao.upd(entity);
		ChokDto<Object> dto = new ChokDto<Object>();
		return dto;
	}	

	@Cacheable(key = "#param")
	public ChokDto<TbDemoGetOneResult> getOne(TbDemoGetOneParam param) 
	{
		TbDemoGetOneResult result = rDao.getOne(param);
		ChokDto<TbDemoGetOneResult> dto = new ChokDto<TbDemoGetOneResult>();
		dto.setData(result);
		return dto;
	}

	@Cacheable(key = "#param")
	public ChokDto<List<TbDemoGetListResult>> getList(TbDemoGetListParam param) 
	{
		List<TbDemoGetListResult> result = rDao.getList(param);
		ChokDto<List<TbDemoGetListResult>> dto = new ChokDto<List<TbDemoGetListResult>>();
		dto.setData(result);
		return dto;
	}
}
