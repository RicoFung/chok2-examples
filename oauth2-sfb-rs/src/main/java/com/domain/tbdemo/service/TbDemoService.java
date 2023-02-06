package com.domain.tbdemo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.domain.tbdemo.dao.TbDemoDao;
import com.domain.tbdemo.model.data.TbDemoGetListData;
import com.domain.tbdemo.model.data.TbDemoGetOneData;
import com.domain.tbdemo.model.entity.TbDemoEntity;
import com.domain.tbdemo.model.query.TbDemoGetListQuery;
import com.domain.tbdemo.model.query.TbDemoGetOneQuery;

import chok.devwork.pojo.ChokDto;

@CacheConfig(cacheNames = {"CACHE_TbDemo"})
@Service(value = "v3TbDemoService")
public class TbDemoService
{
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TbDemoDao dao;

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> create(TbDemoEntity entity)
	{
		dao.create(entity);
		return new ChokDto<Object>();
	}

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> remove(String[] ids)
	{
		dao.remove(ids);
		return new ChokDto<Object>();
	}

	@Caching(evict = { @CacheEvict(value = {"CACHE_TbDemo"}, allEntries = true) })
	public ChokDto<Object> modify(TbDemoEntity entity)
	{
		dao.modify(entity);
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
