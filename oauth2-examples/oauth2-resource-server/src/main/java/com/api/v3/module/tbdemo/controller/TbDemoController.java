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

import com.api.v3.module.tbdemo.controller.ro.TbDemoAddRO;
import com.api.v3.module.tbdemo.controller.ro.TbDemoDelRO;
import com.api.v3.module.tbdemo.controller.ro.TbDemoGetListRO;
import com.api.v3.module.tbdemo.controller.ro.TbDemoGetOneRO;
import com.api.v3.module.tbdemo.controller.ro.TbDemoUpdRO;
import com.api.v3.module.tbdemo.service.TbDemoService;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetListPO;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetOnePO;

import chok.devwork.handler.CHandler;
import chok.devwork.pojo.ChokResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "v3-TbDemo")
@RestController(value = "v3TbDemoController")
@RequestMapping("/api/v3/tbdemo")
public class TbDemoController
{

	// ---------------------------------------------------------------------------------------
	// //
	// value: 指定请求的实际地址， 比如 /action/info之类
	// method： 指定请求的method类型， GET、POST、PUT、DELETE等
	// consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	// produces: 指定返回的内容类型，仅当ro请求头中的(Accept)类型中包含该指定类型才返回
	// ros： 指定ro中必须包含某些参数值是，才让该方法处理
	// headers： 指定ro中必须包含某些指定的header值，才能让该方法处理请求
	// ---------------------------------------------------------------------------------------
	// //

	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(getClass());	

	@Autowired
	private TbDemoService service;

	@Operation(summary = "新增")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokResultDTO<Object> add(@RequestBody @Validated TbDemoAddRO ro, BindingResult br)
	{
		ChokResultDTO<Object> dto = new ChokResultDTO<Object>();
		CHandler.Callback<Object, ChokResultDTO<Object>> callback = new CHandler.Callback<Object, ChokResultDTO<Object>>()
		{
			@Override
			protected ChokResultDTO<Object> process(ChokResultDTO<Object> dto, Authentication auth, Long time) throws Exception
			{
				return service.add(ro);
			}
		};
		return new CHandler<Object, ChokResultDTO<Object>>().execute(ro, br, dto, callback);
	}

	@Operation(summary = "删除")
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokResultDTO<Object> del(@RequestBody @Validated TbDemoDelRO ro, BindingResult br)
	{
		ChokResultDTO<Object> dto = new ChokResultDTO<Object>();
		CHandler.Callback<Object, ChokResultDTO<Object>> callback = new CHandler.Callback<Object, ChokResultDTO<Object>>()
		{
			@Override
			protected ChokResultDTO<Object> process(ChokResultDTO<Object> dto, Authentication auth, Long time) throws Exception
			{
				return service.del(ro);
			}
		};
		return new CHandler<Object, ChokResultDTO<Object>>().execute(ro, br, dto, callback);
	}

	@Operation(summary = "修改")
	@RequestMapping(value = "/upd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokResultDTO<Object> upd(@RequestBody @Validated TbDemoUpdRO ro, BindingResult br)
	{
		ChokResultDTO<Object> dto = new ChokResultDTO<Object>();
		CHandler.Callback<Object, ChokResultDTO<Object>> callback = new CHandler.Callback<Object, ChokResultDTO<Object>>()
		{
			@Override
			protected ChokResultDTO<Object> process(ChokResultDTO<Object> dto, Authentication auth, Long time) throws Exception
			{
				return service.upd(ro);
			}
		};
		return new CHandler<Object, ChokResultDTO<Object>>().execute(ro, br, dto, callback);
	}

	@Operation(summary = "明细")
	@RequestMapping(value = "/getOne", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokResultDTO<TbDemoGetOnePO> getOne(@RequestBody @Validated TbDemoGetOneRO ro, BindingResult br)
	{
		ChokResultDTO<TbDemoGetOnePO> dto = new ChokResultDTO<TbDemoGetOnePO>();
		CHandler.Callback<TbDemoGetOnePO, ChokResultDTO<TbDemoGetOnePO>> callback = new CHandler.Callback<TbDemoGetOnePO, ChokResultDTO<TbDemoGetOnePO>>()
		{
			@Override
			protected ChokResultDTO<TbDemoGetOnePO> process(ChokResultDTO<TbDemoGetOnePO> dto, Authentication auth, Long time) throws Exception
			{
				return service.getOne(ro);
			}
		};
		return new CHandler<TbDemoGetOnePO, ChokResultDTO<TbDemoGetOnePO>>().execute(ro, br, dto, callback);
	}

	@Operation(summary = "列表")
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokResultDTO<List<TbDemoGetListPO>> getList(@RequestBody @Validated TbDemoGetListRO ro, BindingResult br)
	{
		ChokResultDTO<List<TbDemoGetListPO>> dto = new ChokResultDTO<List<TbDemoGetListPO>>();
		CHandler.Callback<List<TbDemoGetListPO>, ChokResultDTO<List<TbDemoGetListPO>>> callback = new CHandler.Callback<List<TbDemoGetListPO>, ChokResultDTO<List<TbDemoGetListPO>>>()
		{
			@Override
			protected ChokResultDTO<List<TbDemoGetListPO>> process(ChokResultDTO<List<TbDemoGetListPO>> dto, Authentication auth, Long time) throws Exception
			{
				return service.getList(ro);
			}
		};
		return new CHandler<List<TbDemoGetListPO>, ChokResultDTO<List<TbDemoGetListPO>>>().execute(ro, br, dto, callback);
	}
}
