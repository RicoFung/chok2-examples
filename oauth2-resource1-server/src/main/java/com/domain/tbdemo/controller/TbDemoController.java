package com.domain.tbdemo.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.tbdemo.model.data.TbDemoGetListData;
import com.domain.tbdemo.model.data.TbDemoGetOneData;
import com.domain.tbdemo.model.entity.TbDemoEntity;
import com.domain.tbdemo.model.param.ParamMapper;
import com.domain.tbdemo.model.param.TbDemoCreateParam;
import com.domain.tbdemo.model.param.TbDemoExportParam;
import com.domain.tbdemo.model.param.TbDemoGetListParam;
import com.domain.tbdemo.model.param.TbDemoGetOneParam;
import com.domain.tbdemo.model.param.TbDemoModifyParam;
import com.domain.tbdemo.model.param.TbDemoRemoveParam;
import com.domain.tbdemo.model.query.TbDemoGetListQuery;
import com.domain.tbdemo.model.query.TbDemoGetOneQuery;
import com.domain.tbdemo.service.TbDemoService;

import chok2.devwork.BaseRestController;
import chok2.devwork.pojo.ChokDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "v3-TbDemo")
@RestController(value = "v3TbDemoController")
@RequestMapping("/api/v3/tbdemo")
public class TbDemoController extends BaseRestController
{
	// --------------------------------------------------------------------------------------- //
	// value: 指定请求的实际地址， 比如 /action/info之类
	// method： 指定请求的method类型， GET、POST、PUT、DELETE等
	// consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	// produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
	// params： 指定request中必须包含某些参数值是，才让该方法处理
	// headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求
	// --------------------------------------------------------------------------------------- //

	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TbDemoService service;

	@Operation(summary = "新增")
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokDto<Object> create(@RequestBody @Validated TbDemoCreateParam param)
	{
		TbDemoEntity entity = ParamMapper.INSTANCE.paramToEntity(param);
		return service.create(entity);
	}

	@Operation(summary = "删除")
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokDto<Object> remove(@RequestBody @Validated TbDemoRemoveParam param)
	{
		return service.remove(param.getTcRowidArray());
	}

	@Operation(summary = "修改")
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokDto<Object> modify(@RequestBody @Validated TbDemoModifyParam param)
	{
		TbDemoEntity entity = ParamMapper.INSTANCE.paramToEntity(param);
		return service.modify(entity);
	}

	@Operation(summary = "明细")
	@RequestMapping(value = "/getOne", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokDto<TbDemoGetOneData> getOne(@RequestBody @Validated TbDemoGetOneParam param)
	{
		TbDemoGetOneQuery query = ParamMapper.INSTANCE.paramToQuery(param);
		return service.getOne(query);
	}

	@Operation(summary = "列表")
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokDto<List<TbDemoGetListData>> getList(@RequestBody @Validated TbDemoGetListParam param)
	{
		TbDemoGetListQuery query = ParamMapper.INSTANCE.paramToQuery(param);
		return service.getList(query);
	}
	
	@Operation(summary = "导出（布局1）")
	@RequestMapping(value = "/export1", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void export1(@RequestBody @Validated TbDemoExportParam param) throws Exception
	{
		TbDemoGetListQuery query = ParamMapper.INSTANCE.paramToQuery(param);
		List<TbDemoGetListData> bizDatasetValue = service.getList(query).getData();
		exportRptOneTable("rpt_demo_bean_single", param.getRptName(), param.getRptFormat(), "bizDatasetKey", bizDatasetValue, Object.class);
	}
	
	@Operation(summary = "导出（布局2）")
	@RequestMapping(value = "/export2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void export2(@RequestBody @Validated TbDemoExportParam param) throws Exception
	{
		TbDemoGetListQuery query = ParamMapper.INSTANCE.paramToQuery(param);
		List<TbDemoGetListData> bizDatasetValue1 = service.getList(query).getData();
		List<TbDemoGetListData> bizDatasetValue2 = service.getList(query).getData();
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
		exportRptMultiTable("rpt_demo_bean_multi", param.getRptName(), param.getRptFormat(), bizDatasetKV, bizDatasetTableKV, Object.class, Object.class);
	}
}
