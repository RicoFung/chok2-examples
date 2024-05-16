package com.domain.customtbdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.customtbdemo.dto.CustomDto;
import com.domain.customtbdemo.model.data.CustomTbDemoGetListData;
import com.domain.customtbdemo.model.query.CustomTbDemoGetListQuery;

@Service
public class CustomTbDemoService
{
	public CustomDto<List<CustomTbDemoGetListData>> getList(CustomTbDemoGetListQuery query) 
	{
		List<CustomTbDemoGetListData> data = new ArrayList<CustomTbDemoGetListData>();
		for (Long i=0l; i<10; i++)
		{
			CustomTbDemoGetListData dataItem = new CustomTbDemoGetListData();
			dataItem.setTcRowid(i);
			dataItem.setTcCode("c"+i);
			dataItem.setTcName("n"+i);
			data.add(dataItem);
		}
		CustomDto<List<CustomTbDemoGetListData>> resultDTO = new CustomDto<List<CustomTbDemoGetListData>>();
		resultDTO.setData(data);
		return resultDTO;
	}
}
