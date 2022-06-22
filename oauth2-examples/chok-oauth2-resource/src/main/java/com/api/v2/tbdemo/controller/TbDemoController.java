package com.api.v2.tbdemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.v2.entity.TbDemo;
import com.api.v2.tbdemo.dto.TbDemoAddDTO;
import com.api.v2.tbdemo.dto.TbDemoDelDTO;
import com.api.v2.tbdemo.dto.TbDemoExpDTO;
import com.api.v2.tbdemo.dto.TbDemoExpRptDTO;
import com.api.v2.tbdemo.dto.TbDemoGetDTO;
import com.api.v2.tbdemo.dto.TbDemoImpDTO;
import com.api.v2.tbdemo.dto.TbDemoQueryDTO;
import com.api.v2.tbdemo.dto.TbDemoUpdDTO;
import com.api.v2.tbdemo.service.TbDemoService;
import com.fasterxml.jackson.core.type.TypeReference;

import chok.common.RestConstants;
import chok.common.RestResult;
import chok.devwork.springboot.BaseRestController;
import chok.util.POIUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "v2-TbDemo")
@RestController(value = "v2TbDemoController")
@RequestMapping("/api/v2/tbdemo")
public class TbDemoController extends BaseRestController<TbDemo>
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
				restResult.setCode(RestConstants.ERROR_CODE1);
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
			restResult.setCode(RestConstants.ERROR_CODE1);
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
				restResult.setCode(RestConstants.ERROR_CODE1);
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
			restResult.setCode(RestConstants.ERROR_CODE1);
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
				restResult.setCode(RestConstants.ERROR_CODE1);
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
				restResult.setCode(RestConstants.ERROR_CODE1);
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
			restResult.setCode(RestConstants.ERROR_CODE1);
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
			restResult.setCode(RestConstants.ERROR_CODE1);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
	
	@Operation(summary = "上传")
	@RequestMapping(value = "/imp", method = RequestMethod.POST, consumes = "multipart/*", headers = "content-type=multipart/form-data")
	public RestResult imp(@RequestParam("file") MultipartFile file, @Parameter(name="json", example = "{\"param1\":\"1\",\"param2\":\"2\"}") @RequestParam("json") String json)
	{
		restResult = new RestResult();
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：filename={}, json={}", file.getOriginalFilename(), json);
			}
			restResult = validImportBefore(file, json, TbDemoImpDTO.class);
			if (!restResult.isSuccess())
			{
				return restResult;
			}
//			String tcUuid = UUID.randomUUID().toString();
			List<TbDemo> impList = new ArrayList<TbDemo>();
			List<String[]> excelList = POIUtil.readExcel(file, 1);
			// Excel 2 List<T>
			for(String[] excelRow : excelList)
			{
				TbDemo tbDemo = new TbDemo();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("tcCode", excelRow[0]);
				m.put("tcName", excelRow[1]);
				org.apache.commons.beanutils.BeanUtils.populate(tbDemo, m);
				impList.add(tbDemo);
			}
			// 分批写入，每批100条
			service.addBatch(impList, 100);
		}
		catch (Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setCode(RestConstants.ERROR_CODE1);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
	
	@Operation(summary = "导出")
	@RequestMapping(value = "/exp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void exp(@RequestBody @Validated TbDemoExpDTO tbDemoExpDTO, BindingResult validResult)
	{
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(tbDemoExpDTO));
			}
			if (validResult.hasErrors())
			{
				throw new Exception(getValidMsgs(validResult));
			}
			// 查询参数
			Map<String, Object> param = restMapper.convertValue(tbDemoExpDTO,
					new TypeReference<Map<String, Object>>()
					{
					});
			if (0 < tbDemoExpDTO.getTcRowids().length)
			{
				param.clear();
				param.put("tcRowidArray", tbDemoExpDTO.getTcRowids());
			}
			// 限制导出数量必须小于1000
			int count = service.getCount(param);
			if (1000 < count)
			{
				throw new Exception("导出数量不能大于1000条！");
			}
			// 查询
			List<TbDemo> list = service.queryDynamic(param);
			// 导出至excel
			export(list, 
					tbDemoExpDTO.getShowFilename(),
					tbDemoExpDTO.getShowTitle(),
					StringUtils.join(tbDemoExpDTO.getShowAlias(), ","),
					StringUtils.join(tbDemoExpDTO.getShowColumns(), ","),
					"xlsx");
		}
		catch (Exception e)
		{
			log.error("<== Exception：{}", e);
		}
	}
	
	@Operation(summary = "导出单个Bean列表（pdf/xlsx/html）")
	@RequestMapping(value = "/expRpt1", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void expRpt1(@RequestBody @Validated TbDemoExpRptDTO tbDemoExpRptDTO, BindingResult validResult)
	{
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(tbDemoExpRptDTO));
			}
			if (validResult.hasErrors())
			{
				throw new Exception(getValidMsgs(validResult));
			}
			// 查询参数
			Map<String, Object> param = restMapper.convertValue(tbDemoExpRptDTO,
					new TypeReference<Map<String, Object>>()
			{
			});
			if (0 < tbDemoExpRptDTO.getTcRowids().length)
			{
				param.clear();
				param.put("tcRowidArray", tbDemoExpRptDTO.getTcRowids());
			}
			// 限制导出数量必须小于1000
			int count = service.getCount(param);
			if (1000 < count)
			{
				throw new Exception("导出数量不能大于1000条！");
			}
			// 查询
			List<TbDemo> bizDatasetValue = service.queryDynamic(param);
			// 导出
			exportRptOneTable("rpt_demo_bean_single", tbDemoExpRptDTO.getRptName(), tbDemoExpRptDTO.getRptFormat(), "bizDatasetKey", bizDatasetValue, Object.class);
		}
		catch (Exception e)
		{
			log.error("<== Exception：{}", e);
		}
	}
	
	@Operation(summary = "导出多个Bean列表（pdf/xlsx/html）")
	@RequestMapping(value = "/expRpt2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void expRpt2(@RequestBody @Validated TbDemoExpRptDTO tbDemoExpRptDTO, BindingResult validResult)
	{
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(tbDemoExpRptDTO));
			}
			if (validResult.hasErrors())
			{
				throw new Exception(getValidMsgs(validResult));
			}
			// 查询参数
			Map<String, Object> param = restMapper.convertValue(tbDemoExpRptDTO,
					new TypeReference<Map<String, Object>>()
			{
			});
			if (0 < tbDemoExpRptDTO.getTcRowids().length)
			{
				param.clear();
				param.put("tcRowidArray", tbDemoExpRptDTO.getTcRowids());
			}
			// 限制导出数量必须小于1000
			int count = service.getCount(param);
			if (1000 < count)
			{
				throw new Exception("导出数量不能大于1000条！");
			}
			// 查询业务数据
			List<TbDemo> bizDatasetValue1 = service.queryDynamic(param);
			List<TbDemo> bizDatasetValue2 = service.queryDynamic(param);
			// rpt 基础控件参数
			Map<String, ?> bizDatasetKV = new HashMap<String, Object>() 
			{
				private static final long serialVersionUID = 1L;
				{
					put("mainField1", "hi world!");
					put("mainField2", "hello world!");
				}
			};
			// rpt Table控件参数
			LinkedHashMap<String, List<?>> bizDatasetTableKV = new LinkedHashMap<String, List<?>>()
			{
				private static final long serialVersionUID = 1L;
				{
					put("bizDatasetKey1", bizDatasetValue1);
					put("bizDatasetKey2", bizDatasetValue2);
				}
			};
			// 导出
			exportRptMultiTable("rpt_demo_bean_multi", tbDemoExpRptDTO.getRptName(), tbDemoExpRptDTO.getRptFormat(), bizDatasetKV, bizDatasetTableKV, Object.class, Object.class);
		}
		catch (Exception e)
		{
			log.error("<== Exception：{}", e);
		}
	}
	
}
