package com.api.v3.tbdemo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.api.v3.tbdemo.pojo.param.TbDemoGetListParam;
import com.api.v3.tbdemo.pojo.param.TbDemoGetOneParam;
import com.api.v3.tbdemo.pojo.result.TbDemoGetListResult;
import com.api.v3.tbdemo.pojo.result.TbDemoGetOneResult;


@Repository(value = "v3TbDemoReadDao")
public class TbDemoReadDao
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;
	
	public TbDemoGetOneResult getOne(TbDemoGetOneParam param) 
	{
		return this.sqlSession.selectOne(getClass().getName()+"."+"getOne", param);
	}
	
	public List<TbDemoGetListResult> getList(TbDemoGetListParam param) 
	{
		return this.sqlSession.selectList(getClass().getName()+"."+"getList", param);
	}
}
