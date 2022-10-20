package com.api.v3.tbdemo.controller.result;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.api.v3.tbdemo.service.result.TbDemoGetOneResultBO;

@Mapper
public interface TbDemoGetOneResultMapper
{
	TbDemoGetOneResultMapper INSTANCE = Mappers.getMapper( TbDemoGetOneResultMapper.class );
	
	TbDemoGetOneResultDTO boToDto(TbDemoGetOneResultBO bo);
}
