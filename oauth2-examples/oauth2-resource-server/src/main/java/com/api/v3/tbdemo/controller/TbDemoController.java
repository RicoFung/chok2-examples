package com.api.v3.tbdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.v3.dao.tbdemo.po.TbDemoGetListResultPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetOneResultPO;
import com.api.v3.tbdemo.dto.TbDemoAddParamDTO;
import com.api.v3.tbdemo.dto.TbDemoDelParamDTO;
import com.api.v3.tbdemo.dto.TbDemoGetListParamDTO;
import com.api.v3.tbdemo.dto.TbDemoGetOneParamDTO;
import com.api.v3.tbdemo.dto.TbDemoUpdParamDTO;
import com.api.v3.tbdemo.service.TbDemoService;

import chok.devwork.springboot.pojo.ChokResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "v3-TbDemo")
@RestController(value = "v3TbDemoController")
@RequestMapping("/api/v3/tbdemo")
public class TbDemoController //extends BaseController
{
	// --------------------------------------------------------------------------------------- //
	// value: 指定请求的实际地址， 比如 /action/info之类
	// method： 指定请求的method类型， GET、POST、PUT、DELETE等
	// consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	// produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
	// params： 指定request中必须包含某些参数值是，才让该方法处理
	// headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求
	// --------------------------------------------------------------------------------------- //
	
//	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TbDemoService service;

	@Operation(summary = "新增")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokResultDTO<Object> add(@RequestBody @Validated TbDemoAddParamDTO paramDTO, BindingResult validResult)
	{
		return new ControllerHandler<Object>().execute(paramDTO, validResult, new ControllerHandler.Callback<Object>() {

			@Override
			protected ChokResultDTO<Object> process(ChokResultDTO<Object> resultDTO, Authentication authentication, Long tcTime)
					throws Exception
			{
				return service.add(paramDTO);
			}
			
		});
	}

	@Operation(summary = "删除")
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokResultDTO<Object> del(@RequestBody @Validated TbDemoDelParamDTO paramDTO, BindingResult validResult) 
	{
		return new ControllerHandler<Object>().execute(paramDTO, validResult, new ControllerHandler.Callback<Object>() {

			@Override
			protected ChokResultDTO<Object> process(ChokResultDTO<Object> resultDTO, Authentication authentication, Long tcTime)
					throws Exception
			{
				return service.del(paramDTO);
			}
			
		});
	}

	@Operation(summary = "修改")
	@RequestMapping(value = "/upd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokResultDTO<Object> upd(@RequestBody @Validated TbDemoUpdParamDTO paramDTO, BindingResult validResult) 
	{
		return new ControllerHandler<Object>().execute(paramDTO, validResult, new ControllerHandler.Callback<Object>() {

			@Override
			protected ChokResultDTO<Object> process(ChokResultDTO<Object> resultDTO, Authentication authentication, Long tcTime)
					throws Exception
			{
				return service.upd(paramDTO);
			}
			
		});
	}
	
	@Operation(summary = "明细")
	@RequestMapping(value = "/getOne", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokResultDTO<TbDemoGetOneResultPO> getOne(@RequestBody @Validated TbDemoGetOneParamDTO paramDTO, BindingResult validResult) 
	{
		return new ControllerHandler<TbDemoGetOneResultPO>().execute(paramDTO, validResult, new ControllerHandler.Callback<TbDemoGetOneResultPO>() {

			@Override
			protected ChokResultDTO<TbDemoGetOneResultPO> process(ChokResultDTO<TbDemoGetOneResultPO> resultDTO, Authentication authentication, Long tcTime)
					throws Exception
			{
				return service.getOne(paramDTO);
			}
			
		});
	}
	
	@Operation(summary = "列表")
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokResultDTO<List<TbDemoGetListResultPO>> getList(@RequestBody @Validated TbDemoGetListParamDTO paramDTO, BindingResult validResult) 
	{
		return new ControllerHandler<List<TbDemoGetListResultPO>>().execute(paramDTO, validResult, new ControllerHandler.Callback<List<TbDemoGetListResultPO>>() {

			@Override
			protected ChokResultDTO<List<TbDemoGetListResultPO>> process(ChokResultDTO<List<TbDemoGetListResultPO>> resultDTO, Authentication authentication, Long tcTime)
					throws Exception
			{
				return service.getList(paramDTO);
			}
			
		});
	}
	
}
