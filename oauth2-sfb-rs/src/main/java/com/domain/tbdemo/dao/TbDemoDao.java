package com.domain.tbdemo.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import chok.devwork.BaseDaoV2;


@Repository(value = "v3TbDemoDao")
public class TbDemoDao extends BaseDaoV2
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	protected String getSqlNamespace()
	{
		return getClass().getName();
	}
	
}
