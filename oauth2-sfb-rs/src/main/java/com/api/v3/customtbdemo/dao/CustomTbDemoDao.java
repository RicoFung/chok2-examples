package com.api.v3.customtbdemo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.api.v3.customtbdemo.domain.data.CustomTbDemoGetListData;
import com.api.v3.customtbdemo.domain.query.CustomTbDemoGetListQuery;

@Repository(value = "v3CustomTbDemoDao")
public class CustomTbDemoDao
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;
	
	public List<CustomTbDemoGetListData> getList(CustomTbDemoGetListQuery query) 
	{
		return this.sqlSession.selectList(getClass().getName()+"."+"getList", query);
	}
}
