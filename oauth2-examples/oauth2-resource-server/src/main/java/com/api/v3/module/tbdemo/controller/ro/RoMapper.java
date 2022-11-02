package com.api.v3.module.tbdemo.controller.ro;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.api.v3.repository.tbdemo.dao.param.TbDemoGetListParam;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetOneParam;
import com.api.v3.repository.tbdemo.entity.TbDemo;

import org.mapstruct.Mapping;

@Mapper
public interface RoMapper
{
	RoMapper INSTANCE = Mappers.getMapper( RoMapper.class );
	
	@Mapping(target = "tcRowid", ignore = true)
	TbDemo roToEntity(TbDemoAddRO ro);
	
	TbDemo roToEntity(TbDemoUpdRO ro);
	
	TbDemoGetOneParam roToParam(TbDemoGetOneRO ro);
	
	@Mapping(target = "dynamicWhere.tcRowid", ignore = true)
	TbDemoGetListParam roToParam(TbDemoGetListRO ro);
}
