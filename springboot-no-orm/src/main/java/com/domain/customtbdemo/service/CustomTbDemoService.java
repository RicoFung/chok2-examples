package com.domain.customtbdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.customtbdemo.dto.custom.CustomDto;
import com.domain.customtbdemo.dto.custom1.Custom1Dto;
import com.domain.customtbdemo.dto.custom1.Custom1DtoData;
import com.domain.customtbdemo.model.data.CustomTbDemoData;
import com.domain.customtbdemo.model.query.CustomTbDemoGetListQuery;

@Service
public class CustomTbDemoService
{
	public CustomDto<List<CustomTbDemoData>> getList(CustomTbDemoGetListQuery query) 
	{
		List<CustomTbDemoData> data = new ArrayList<CustomTbDemoData>();
		for (Long i=0l; i<10; i++)
		{
			CustomTbDemoData dataItem = new CustomTbDemoData();
			dataItem.setTcRowid(i);
			dataItem.setTcCode("c"+i);
			dataItem.setTcName("n"+i);
			data.add(dataItem);
		}
		CustomDto<List<CustomTbDemoData>> resultDTO = new CustomDto<List<CustomTbDemoData>>();
		resultDTO.setData(data);
		return resultDTO;
	}
	
	public Custom1Dto<Custom1DtoData<CustomTbDemoData>> getList1(CustomTbDemoGetListQuery query) 
	{
		List<CustomTbDemoData> list = new ArrayList<CustomTbDemoData>();
		for (Long i=0l; i<10; i++)
		{
			CustomTbDemoData listItem = new CustomTbDemoData();
			listItem.setTcRowid(i);
			listItem.setTcCode("c"+i);
			listItem.setTcName("n"+i);
			list.add(listItem);
		}
		Custom1DtoData<CustomTbDemoData> data = new Custom1DtoData<CustomTbDemoData>();
		data.setList(list);
		data.setTotal(list.size());
		
		Custom1Dto<Custom1DtoData<CustomTbDemoData>> resultDTO = new Custom1Dto<Custom1DtoData<CustomTbDemoData>>();
		resultDTO.setData(data);
		
		return resultDTO;
	}
}
