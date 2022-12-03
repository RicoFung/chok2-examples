package com.api.v3.tbdemo.pojo.param;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.api.v3.tbdemo.pojo.TbDemo;

@Mapper
public interface ParamMapper
{
	ParamMapper INSTANCE = Mappers.getMapper( ParamMapper.class );
	
	@Mapping(target = "tcRowid", ignore = true)
	TbDemo paramToEntity(TbDemoAddParam param);
	
	TbDemo paramToEntity(TbDemoUpdParam param);
	
}
