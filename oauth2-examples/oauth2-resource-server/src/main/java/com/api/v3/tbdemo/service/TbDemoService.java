package com.api.v3.tbdemo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	TbDemoWriteDao wDao;
	@Autowired
	TbDemoReadDao rDao;

	@Caching(evict = { @CacheEvict(allEntries = true) })
//	public void add(TbDemoAddParam param)
//	{
//		TbDemo entity = ParamMapper.INSTANCE.paramToEntity(param);
//		wDao.add(entity);
//	}
	public ChokDto<Object> add(TbDemoAddParam param)
	{
		ChokDto<Object> dto = new ChokDto<Object>();
		TbDemo entity = ParamMapper.INSTANCE.paramToEntity(param);
		wDao.add(entity);
		return dto;
	}

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> del(TbDemoDelParam param)
	{
		ChokDto<Object> dto = new ChokDto<Object>();
		wDao.del(param.getTcRowidArray());
		return dto;
	}

	@Caching(evict = { @CacheEvict(value = {"Cache_TbDemo", "Cache_CustomTbDemo"}, allEntries = true) })
	public ChokDto<Object> upd(TbDemoUpdParam param)
	{
		ChokDto<Object> dto = new ChokDto<Object>();
		TbDemo entity = ParamMapper.INSTANCE.paramToEntity(param);
		wDao.upd(entity);
		return dto;
	}	

	@Cacheable(key = "#param")
	public ChokDto<TbDemoGetOneResult> getOne(TbDemoGetOneParam param) 
	{
		ChokDto<TbDemoGetOneResult> dto = new ChokDto<TbDemoGetOneResult>();
		TbDemoGetOneResult result = rDao.getOne(param);
		dto.setData(result);
		return dto;
	}

	@Cacheable(key = "#param")
	public ChokDto<List<TbDemoGetListResult>> getList(TbDemoGetListParam param) 
	{
		ChokDto<List<TbDemoGetListResult>> dto = new ChokDto<List<TbDemoGetListResult>>();
		List<TbDemoGetListResult> result = rDao.getList(param);
		dto.setData(result);
		return dto;
	}
}
