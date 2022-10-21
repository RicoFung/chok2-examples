package com.api.v3.tbdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.v3.tbdemo.dto.TbDemoGetListParamDTO;
import com.api.v3.tbdemo.dto.TbDemoGetListResultDTO;
import com.api.v3.tbdemo.dto.TbDemoGetOneParamDTO;
import com.api.v3.tbdemo.dto.TbDemoGetOneResultDTO;
import com.api.v3.tbdemo.service.TbDemoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import chok.common.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "v3-TbDemo")
@RestController(value = "v3TbDemoController")
@RequestMapping("/api/v3/tbdemo")
public class TbDemoController //extends BaseRestController<TbDemo>
{
	// --------------------------------------------------------------------------------------- //
	// value: 指定请求的实际地址， 比如 /action/info之类
	// method： 指定请求的method类型， GET、POST、PUT、DELETE等
	// consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	// produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
	// params： 指定request中必须包含某些参数值是，才让该方法处理
	// headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求
	// --------------------------------------------------------------------------------------- //
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TbDemoService service;

	private ObjectMapper objMapper = new ObjectMapper();

	@Operation(summary = "明细")
	@RequestMapping(value = "/getOne", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public TbDemoGetOneResultDTO getOne(@RequestBody @Validated TbDemoGetOneParamDTO paramDTO, BindingResult validResult) 
	{
		TbDemoGetOneResultDTO resultDTO = new TbDemoGetOneResultDTO();
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", objMapper.writeValueAsString(paramDTO));
			}
			if (validResult.hasErrors()) 
			{
				resultDTO.setSuccess(false);
				resultDTO.setCode(RestConstants.ERROR_CODE1);
				resultDTO.setMsg("自动校验不通过！");
//				restResult.setMsg(getValidMsgs(validResult));
				return resultDTO;
			}
			resultDTO = service.getOne(paramDTO);
		}
		catch(Exception e)
		{
			log.error("<== Exception：{}", e);
			resultDTO.setSuccess(false);
			resultDTO.setCode(RestConstants.ERROR_CODE1);
			resultDTO.setMsg(e.getMessage());
		}
		return resultDTO;
	}
	
	@Operation(summary = "列表")
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public TbDemoGetListResultDTO getList(@RequestBody @Validated TbDemoGetListParamDTO paramDTO, BindingResult validResult) 
	{
		TbDemoGetListResultDTO resultDTO = new TbDemoGetListResultDTO();
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", objMapper.writeValueAsString(paramDTO));
			}
			if (validResult.hasErrors()) 
			{
				resultDTO.setSuccess(false);
				resultDTO.setCode(RestConstants.ERROR_CODE1);
				resultDTO.setMsg("自动校验不通过！");
//				restResult.setMsg(getValidMsgs(validResult));
				return resultDTO;
			}
			resultDTO = service.getList(paramDTO);
		}
		catch(Exception e)
		{
			log.error("<== Exception：{}", e);
			resultDTO.setSuccess(false);
			resultDTO.setCode(RestConstants.ERROR_CODE1);
			resultDTO.setMsg(e.getMessage());
		}
		return resultDTO;
	}
	
	
}
