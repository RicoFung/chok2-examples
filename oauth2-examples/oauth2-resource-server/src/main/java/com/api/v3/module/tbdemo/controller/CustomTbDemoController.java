package com.api.v3.module.tbdemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.v3.module.tbdemo.controller.dto.CustomResultDTO;
import com.api.v3.module.tbdemo.controller.ro.TbDemoGetListRO;
import com.api.v3.module.tbdemo.service.CustomTbDemoService;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetListPO;

import chok.devwork.handler.CHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "v3-CustomTbDemo")
@RestController(value = "v3CustomTbDemoController")
@RequestMapping("/api/v3/customtbdemo")
public class CustomTbDemoController
{

	// ---------------------------------------------------------------------------------------
	// //
	// value: 指定请求的实际地址， 比如 /action/info之类
	// method： 指定请求的method类型， GET、POST、PUT、DELETE等
	// consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	// produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
	// params： 指定request中必须包含某些参数值是，才让该方法处理
	// headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求
	// ---------------------------------------------------------------------------------------
	// //

	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(getClass());	

	@Autowired
	private CustomTbDemoService service;

	@Operation(summary = "列表")
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public CustomResultDTO<List<TbDemoGetListPO>> getEpoList(@RequestBody @Validated TbDemoGetListRO ro, BindingResult br)
	{
		CustomResultDTO<List<TbDemoGetListPO>> dto = new CustomResultDTO<List<TbDemoGetListPO>>();
		CHandler.Callback<List<TbDemoGetListPO>, CustomResultDTO<List<TbDemoGetListPO>>> callback = new CHandler.Callback<List<TbDemoGetListPO>, CustomResultDTO<List<TbDemoGetListPO>>>()
		{
			@Override
			protected CustomResultDTO<List<TbDemoGetListPO>> process(CustomResultDTO<List<TbDemoGetListPO>> dto, Authentication auth, Long time) throws Exception
			{
				return service.getList(ro);
			}
		};
		return new CHandler<List<TbDemoGetListPO>, CustomResultDTO<List<TbDemoGetListPO>>>().execute(ro, br, dto, callback);
	}

}
