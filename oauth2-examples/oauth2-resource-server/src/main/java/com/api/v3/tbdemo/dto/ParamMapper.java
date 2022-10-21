package com.api.v3.tbdemo.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.api.v3.dao.tbdemo.po.TbDemoGetListParamPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetOneParamPO;

@Mapper
public interface ParamMapper
{
	ParamMapper INSTANCE = Mappers.getMapper( ParamMapper.class );
	
	TbDemoGetOneParamPO dtoToPo(TbDemoGetOneParamDTO dto);
	
	TbDemoGetListParamPO dtoToPo(TbDemoGetListParamDTO dto);
}
