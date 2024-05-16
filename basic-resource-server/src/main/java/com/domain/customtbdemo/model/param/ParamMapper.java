package com.domain.customtbdemo.model.param;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.domain.customtbdemo.model.query.CustomTbDemoGetListQuery;

@Mapper
public interface ParamMapper
{
	ParamMapper INSTANCE = Mappers.getMapper( ParamMapper.class );
	
	CustomTbDemoGetListQuery paramToQuery(CustomTbDemoGetListParam param);
	
}
