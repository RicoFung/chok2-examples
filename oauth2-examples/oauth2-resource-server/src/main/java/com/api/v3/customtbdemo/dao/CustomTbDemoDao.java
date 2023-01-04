package com.api.v3.customtbdemo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.api.v3.customtbdemo.domain.param.CustomTbDemoGetListParam;
import com.api.v3.customtbdemo.domain.result.CustomTbDemoGetListResult;

@Repository(value = "v3CustomTbDemoReadDao")
public class CustomTbDemoDao
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;
	
	public List<CustomTbDemoGetListResult> getList(CustomTbDemoGetListParam param) 
	{
		return this.sqlSession.selectList(getClass().getName()+"."+"getList", param);
	}
}
