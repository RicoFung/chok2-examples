package com.api.v3.tbdemo.domain.param;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.api.v3.tbdemo.domain.entity.TbDemoEntity;
import com.api.v3.tbdemo.domain.query.TbDemoGetListQuery;
import com.api.v3.tbdemo.domain.query.TbDemoGetOneQuery;

@Mapper
public interface ParamMapper
{
	ParamMapper INSTANCE = Mappers.getMapper( ParamMapper.class );
	
	@Mapping(target = "tcRowid", ignore = true)
	TbDemoEntity paramToEntity(TbDemoAddParam param);
	
	TbDemoEntity paramToEntity(TbDemoUpdParam param);
	
	TbDemoGetOneQuery paramToQuery(TbDemoGetOneParam param);
	
	TbDemoGetListQuery paramToQuery(TbDemoGetListParam param);
	
}
