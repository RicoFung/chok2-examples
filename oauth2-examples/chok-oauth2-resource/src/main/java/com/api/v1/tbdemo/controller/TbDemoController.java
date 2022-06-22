package com.api.v1.tbdemo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.v1.tbdemo.dto.TbDemoAddDTO;
import com.api.v1.tbdemo.dto.TbDemoDelDTO;
import com.api.v1.tbdemo.dto.TbDemoGetDTO;
import com.api.v1.tbdemo.dto.TbDemoQueryDTO;
import com.api.v1.tbdemo.dto.TbDemoUpdDTO;
import com.api.v1.tbdemo.entity.TbDemo;
import com.api.v1.tbdemo.service.TbDemoService;
import com.fasterxml.jackson.core.type.TypeReference;

import chok.common.RestResult;
import chok.devwork.springboot.BaseRestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "v1-TbDemo")
@RestController(value = "v1TbDemoController")
@RequestMapping("/api/v1/tbdemo")
public class TbDemoController extends BaseRestController<TbDemo>
{
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TbDemoService service;

	
	@Operation(summary = "新增")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult add(@RequestBody @Validated TbDemoAddDTO tbDemoAddDTO, BindingResult validResult)
	{
		restResult = new RestResult();
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(tbDemoAddDTO));
			}
			if (validResult.hasErrors()) 
			{
				restResult.setSuccess(false);
				restResult.setMsg(getValidMsgs(validResult));
				return restResult;
			}
			TbDemo tbDemo = new TbDemo();
			BeanUtils.copyProperties(tbDemoAddDTO, tbDemo);
			service.add(tbDemo);
		}
		catch(Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}

	@Operation(summary = "删除")
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult del(@RequestBody @Validated TbDemoDelDTO tbDemoDelDTO, BindingResult validResult) 
	{
		restResult = new RestResult();
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(tbDemoDelDTO));
			}
			if (validResult.hasErrors()) 
			{
				restResult.setSuccess(false);
				restResult.setMsg(getValidMsgs(validResult));
				return restResult;
			}
			service.del(tbDemoDelDTO.getTcRowids());
			restResult.setSuccess(true);
		}
		catch(Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}

	@Operation(summary = "修改")
	@RequestMapping(value = "/upd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult upd(@RequestBody @Validated TbDemoUpdDTO tbDemoUpdDTO, BindingResult validResult) 
	{
		restResult = new RestResult();
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(tbDemoUpdDTO));
			}
			if (validResult.hasErrors()) 
			{
				restResult.setSuccess(false);
				restResult.setMsg(getValidMsgs(validResult));
				return restResult;
			}
			TbDemo tbDemo = new TbDemo();
			BeanUtils.copyProperties(tbDemoUpdDTO, tbDemo);
			service.upd(tbDemo);
		}
		catch(Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}

	@Operation(summary = "明细")
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult get(@RequestBody @Validated TbDemoGetDTO tbDemoGetDTO, BindingResult validResult) 
	{
		restResult = new RestResult();
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(tbDemoGetDTO));
			}
			if (validResult.hasErrors()) 
			{
				restResult.setSuccess(false);
				restResult.setMsg(getValidMsgs(validResult));
				return restResult;
			}
			Map<String, Object> param = restMapper.convertValue(tbDemoGetDTO,
					new TypeReference<Map<String, Object>>()
			{
			});
			restResult.put("row", service.getDynamic(param));
		}
		catch(Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
	
	@Operation(summary = "列表")
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult query(@RequestBody TbDemoQueryDTO tbDemoQueryDTO)
	{
		restResult = new RestResult();
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(tbDemoQueryDTO));
			}
			Map<String, Object> param = restMapper.convertValue(tbDemoQueryDTO, new TypeReference<Map<String, Object>>(){});
	        restResult.put("total", service.getCount(param));
			restResult.put("rows", service.queryDynamic(param));
		}
		catch (Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
}
