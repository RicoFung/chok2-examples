package com.api.procdemo.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.api.procdemo.entity.ProcDemo;

import chok.devwork.BaseDao;

@Repository
public class ProcDemoDao extends BaseDao<ProcDemo,Long>
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<ProcDemo> getEntityClass()
	{
		return ProcDemo.class;
	}
	
	public Map<Object, Object> pQuery(Map<String, Object> inParam)
	{
		// proc 参数Map
		Map<Object, Object> procParam = new LinkedHashMap<Object, Object>();
		// proc 入参
		procParam.putAll(inParam);
		// proc 出参
		procParam.put("v_out_data", new String());
		procParam.put("v_out_success", new String());
		procParam.put("v_out_msg", new String());
		procParam.put("v_out_list1", new ArrayList<Map<String, Object>>());
		procParam.put("v_out_list2", new ArrayList<Map<String, Object>>());
		this.query("pQuery", procParam);
		return procParam;
	}
}
