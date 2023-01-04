package com.api.v3.customtbdemo.domain.result;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.api.v3.customtbdemo.domain.param.CustomTbDemoGetListParam;
import com.api.v3.customtbdemo.domain.query.CustomTbDemoGetListQuery;

@Mapper
public interface ParamMapper
{
	ParamMapper INSTANCE = Mappers.getMapper( ParamMapper.class );
	
	CustomTbDemoGetListQuery paramToQuery(CustomTbDemoGetListParam param);
}
