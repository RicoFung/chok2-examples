package com.api.v3.tbdemo.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.api.v3.dao.tbdemo.po.TbDemoGetListParamPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetOneParamPO;
import com.api.v3.entity.TbDemo;
import org.mapstruct.Mapping;

@Mapper
public interface ParamMapper
{
	ParamMapper INSTANCE = Mappers.getMapper( ParamMapper.class );
	
	@Mapping(target = "tcRowid", ignore = true)
	TbDemo dtoToPo(TbDemoAddParamDTO dto);
	
	TbDemo dtoToPo(TbDemoUpdParamDTO dto);
	
	TbDemoGetOneParamPO dtoToPo(TbDemoGetOneParamDTO dto);
	
	TbDemoGetListParamPO dtoToPo(TbDemoGetListParamDTO dto);
}
