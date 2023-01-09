package com.api.v3.customtbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.v3.customtbdemo.dao.CustomTbDemoDao;
import com.api.v3.customtbdemo.domain.data.CustomTbDemoGetListData;
import com.api.v3.customtbdemo.domain.query.CustomTbDemoGetListQuery;
import com.api.v3.customtbdemo.dto.CustomDto;

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
