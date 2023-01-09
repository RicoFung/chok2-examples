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

import com.api.v3.tbdemo.dao.TbDemoDao;
import com.api.v3.tbdemo.domain.data.TbDemoGetListData;
import com.api.v3.tbdemo.domain.data.TbDemoGetOneData;
import com.api.v3.tbdemo.domain.entity.TbDemoEntity;
import com.api.v3.tbdemo.domain.query.TbDemoGetListQuery;
import com.api.v3.tbdemo.domain.query.TbDemoGetOneQuery;

import chok.devwork.pojo.ChokDto;

@CacheConfig(cacheNames = {"Cache_TbDemo"})
@Service
public class TbDemoService
{
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	TbDemoDao dao;

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> add(TbDemoEntity entity)
	{
		dao.add(entity);
		return new ChokDto<Object>();
	}

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> del(String[] ids)
	{
		dao.del(ids);
		return new ChokDto<Object>();
	}

	@Caching(evict = { @CacheEvict(value = {"Cache_TbDemo", "Cache_CustomTbDemo"}, allEntries = true) })
	public ChokDto<Object> upd(TbDemoEntity entity)
	{
		dao.upd(entity);
		return new ChokDto<Object>();
	}	

	@Cacheable(key = "#query")
	public ChokDto<TbDemoGetOneData> getOne(TbDemoGetOneQuery query) 
	{
		TbDemoGetOneData data = dao.getOne(query);
		return new ChokDto<TbDemoGetOneData>(data);
	}

	@Cacheable(key = "#query")
	public ChokDto<List<TbDemoGetListData>> getList(TbDemoGetListQuery query) 
	{
		List<TbDemoGetListData> data = dao.getList(query);
		return new ChokDto<List<TbDemoGetListData>>(data);
	}
}
