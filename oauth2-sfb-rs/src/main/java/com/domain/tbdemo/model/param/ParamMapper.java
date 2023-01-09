package com.domain.tbdemo.model.param;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.domain.tbdemo.model.entity.TbDemoEntity;
import com.domain.tbdemo.model.query.TbDemoGetListQuery;
import com.domain.tbdemo.model.query.TbDemoGetOneQuery;

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
