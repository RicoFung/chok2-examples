package com.domain.customtbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.domain.customtbdemo.dao.CustomTbDemoDao;
import com.domain.customtbdemo.dto.CustomDto;
import com.domain.customtbdemo.model.data.CustomTbDemoGetListData;
import com.domain.customtbdemo.model.query.CustomTbDemoGetListQuery;

@CacheConfig(cacheNames = {"Cache_CustomTbDemo"})
@Service
public class CustomTbDemoService
{
	@Autowired
	CustomTbDemoDao dao;

	@Cacheable(key = "#query")
	public CustomDto<List<CustomTbDemoGetListData>> getList(CustomTbDemoGetListQuery query) 
	{
		List<CustomTbDemoGetListData> result = dao.getList(query);
		CustomDto<List<CustomTbDemoGetListData>> resultDTO = new CustomDto<List<CustomTbDemoGetListData>>();
		resultDTO.setData(result);
		return resultDTO;
	}
}
