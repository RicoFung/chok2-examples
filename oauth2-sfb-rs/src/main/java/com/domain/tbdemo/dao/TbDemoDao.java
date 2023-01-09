package com.domain.tbdemo.dao;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import chok.devwork.BaseDaoPlus;

@Repository(value = "v3TbDemoDao")
public class TbDemoDao extends BaseDaoPlus
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
